import 'package:flame/components.dart';

enum CharacterState {
  idle,
  run,
  attack,
  hit,
  jump,
  fall,
}

enum Direction {
  left,
  right,
}

abstract class CharacterStateBase {
  CharacterState get stateType;
  
  void onEnter(Character character);
  void onUpdate(double dt, Character character);
  void onExit(Character character);
  bool canTransitionTo(CharacterState newState);
}

class IdleState extends CharacterStateBase {
  @override
  CharacterState get stateType => CharacterState.idle;

  @override
  void onEnter(Character character) {
    character.velocity.setZero();
  }

  @override
  void onUpdate(double dt, Character character) {
    if (character.isMoving) {
      character.changeState(CharacterState.run);
    }
    
    if (character.isAttacking) {
      character.changeState(CharacterState.attack);
    }
    
    if (character.isHit) {
      character.changeState(CharacterState.hit);
    }
    
    if (character.isJumping) {
      character.changeState(CharacterState.jump);
    }
  }

  @override
  void onExit(Character character) {}

  @override
  bool canTransitionTo(CharacterState newState) {
    return newState != CharacterState.idle;
  }
}

class RunState extends CharacterStateBase {
  @override
  CharacterState get stateType => CharacterState.run;

  @override
  void onEnter(Character character) {
    character.isMoving = true;
  }

  @override
  void onUpdate(double dt, Character character) {
    if (!character.isMoving) {
      character.changeState(CharacterState.idle);
    }
    
    if (character.isAttacking) {
      character.changeState(CharacterState.attack);
    }
    
    if (character.isHit) {
      character.changeState(CharacterState.hit);
    }
    
    if (character.isJumping) {
      character.changeState(CharacterState.jump);
    }
    
    final moveSpeed = character.moveSpeed * dt;
    if (character.direction == Direction.left) {
      character.position.x -= moveSpeed;
    } else {
      character.position.x += moveSpeed;
    }
  }

  @override
  void onExit(Character character) {
    character.isMoving = false;
  }

  @override
  bool canTransitionTo(CharacterState newState) {
    return newState != CharacterState.run;
  }
}

class AttackState extends CharacterStateBase {
  double _attackTimer = 0.0;
  static const double attackDuration = 0.5;

  @override
  CharacterState get stateType => CharacterState.attack;

  @override
  void onEnter(Character character) {
    _attackTimer = 0.0;
    character.isAttacking = true;
  }

  @override
  void onUpdate(double dt, Character character) {
    _attackTimer += dt;
    
    if (_attackTimer >= attackDuration) {
      character.isAttacking = false;
      character.changeState(CharacterState.idle);
    }
    
    if (character.isHit) {
      character.isAttacking = false;
      character.changeState(CharacterState.hit);
    }
  }

  @override
  void onExit(Character character) {
    character.isAttacking = false;
  }

  @override
  bool canTransitionTo(CharacterState newState) {
    return newState == CharacterState.hit;
  }
}

class HitState extends CharacterStateBase {
  double _hitTimer = 0.0;
  static const double hitDuration = 0.3;

  @override
  CharacterState get stateType => CharacterState.hit;

  @override
  void onEnter(Character character) {
    _hitTimer = 0.0;
    character.isHit = true;
    
    final knockbackForce = 100.0;
    if (character.direction == Direction.left) {
      character.velocity.x = knockbackForce;
    } else {
      character.velocity.x = -knockbackForce;
    }
    character.velocity.y = 50.0;
  }

  @override
  void onUpdate(double dt, Character character) {
    _hitTimer += dt;
    
    character.position += character.velocity * dt;
    character.velocity.y -= 500.0 * dt;
    
    if (_hitTimer >= hitDuration) {
      character.isHit = false;
      character.changeState(CharacterState.idle);
    }
  }

  @override
  void onExit(Character character) {
    character.isHit = false;
    character.velocity.setZero();
  }

  @override
  bool canTransitionTo(CharacterState newState) {
    return newState == CharacterState.idle || newState == CharacterState.hit;
  }
}

class JumpState extends CharacterStateBase {
  double _jumpTimer = 0.0;
  static const double jumpDuration = 0.8;

  @override
  CharacterState get stateType => CharacterState.jump;

  @override
  void onEnter(Character character) {
    _jumpTimer = 0.0;
    character.isJumping = true;
    character.velocity.y = -400.0;
  }

  @override
  void onUpdate(double dt, Character character) {
    _jumpTimer += dt;
    
    character.velocity.y += 800.0 * dt;
    character.position += character.velocity * dt;
    
    final moveSpeed = character.moveSpeed * dt;
    if (character.direction == Direction.left) {
      character.position.x -= moveSpeed;
    } else {
      character.position.x += moveSpeed;
    }
    
    if (character.isAttacking) {
      character.changeState(CharacterState.attack);
    }
    
    if (character.isHit) {
      character.changeState(CharacterState.hit);
    }
    
    if (character.velocity.y > 0 && character.isOnGround) {
      character.isJumping = false;
      character.changeState(CharacterState.idle);
    }
  }

  @override
  void onExit(Character character) {
    character.isJumping = false;
    character.velocity.y = 0;
  }

  @override
  bool canTransitionTo(CharacterState newState) {
    return newState == CharacterState.idle || 
           newState == CharacterState.attack || 
           newState == CharacterState.hit;
  }
}

class Character extends PositionComponent with HasGameRef {
  final CharacterStateMachine stateMachine = CharacterStateMachine();  
  Vector2 velocity = Vector2.zero();
  Direction direction = Direction.right;
  
  bool isMoving = false;
  bool isAttacking = false;
  bool isHit = false;
  bool isJumping = false;
  bool isOnGround = false;
  
  double moveSpeed = 200.0;
  double jumpForce = 400.0;
  double gravity = 800.0;
  
  int health = 100;
  int maxHealth = 100;
  
  CharacterStateBase? currentState;

  Character() : super(size: Vector2(32, 64), anchor: Anchor.center);

  @override
  Future<void> onLoad() async {
    stateMachine.initialize(this);
    changeState(CharacterState.idle);
  }

  @override
  void update(double dt) {
    currentState?.onUpdate(dt, this);
    
    velocity.y += gravity * dt;
    position += velocity * dt;
    
    if (position.y > 500) {
      position.y = 500;
      velocity.y = 0;
      isOnGround = true;
    }
  }

  void changeState(CharacterState newState) {
    if (currentState?.canTransitionTo(newState) ?? true) {
      currentState?.onExit(this);
      currentState = stateMachine.getState(newState);
      currentState?.onEnter(this);
    }
  }

  void moveLeft() {
    direction = Direction.left;
    isMoving = true;
  }

  void moveRight() {
    direction = Direction.right;
    isMoving = true;
  }

  void stopMoving() {
    isMoving = false;
  }

  void attack() {
    if (!isAttacking && !isHit) {
      isAttacking = true;
    }
  }

  void jump() {
    if (isOnGround && !isHit) {
      isJumping = true;
      isOnGround = false;
    }
  }

  void takeDamage(int damage) {
    health -= damage;
    if (health < 0) {
      health = 0;
    }
    isHit = true;
  }

  void reset() {
    health = maxHealth;
    velocity.setZero();
    changeState(CharacterState.idle);
  }
}

class CharacterStateMachine {
  final Map<CharacterState, CharacterStateBase> _states = {};

  void initialize(Character character) {
    _states[CharacterState.idle] = IdleState();
    _states[CharacterState.run] = RunState();
    _states[CharacterState.attack] = AttackState();
    _states[CharacterState.hit] = HitState();
    _states[CharacterState.jump] = JumpState();
  }

  CharacterStateBase? getState(CharacterState state) {
    return _states[state];
  }
}
