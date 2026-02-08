import 'package:flutter_test/flutter_test.dart';
import 'package:flame/components.dart';
import 'package:dnf_flutter/game/character.dart';
import 'package:dnf_flutter/game/impact_system.dart';

void main() {
  group('Character State Machine Tests', () {
    late Character character;

    setUp(() async {
      character = Character();
      await character.onLoad();
    });

    test('should initialize with idle state', () {
      expect(character.currentState?.stateType, CharacterState.idle);
    });

    test('should transition from idle to run when moving', () {
      character.moveRight();
      character.currentState?.onUpdate(0.016, character);

      expect(character.currentState?.stateType, CharacterState.run);
      expect(character.direction, Direction.right);
    });

    test('should transition from run to idle when stopped', () {
      character.moveRight();
      character.currentState?.onUpdate(0.016, character);
      character.stopMoving();
      character.currentState?.onUpdate(0.016, character);

      expect(character.currentState?.stateType, CharacterState.idle);
    });

    test('should transition to attack state when attacking', () {
      character.attack();
      character.currentState?.onUpdate(0.016, character);

      expect(character.currentState?.stateType, CharacterState.attack);
      expect(character.isAttacking, true);
    });

    test('should transition to hit state when taking damage', () {
      character.takeDamage(20);
      character.currentState?.onUpdate(0.016, character);

      expect(character.currentState?.stateType, CharacterState.hit);
      expect(character.isHit, true);
      expect(character.health, 80);
    });

    test('should transition to jump state when jumping', () {
      character.isOnGround = true;
      character.jump();
      character.currentState?.onUpdate(0.016, character);

      expect(character.currentState?.stateType, CharacterState.jump);
      expect(character.isJumping, true);
      expect(character.isOnGround, false);
    });

    test('should not allow attack state to transition to run', () {
      character.attack();
      character.currentState?.onUpdate(0.016, character);

      final canTransition = character.currentState?.canTransitionTo(
        CharacterState.run,
      );

      expect(canTransition, false);
    });

    test('should allow hit state to transition from any state', () {
      character.attack();
      character.currentState?.onUpdate(0.016, character);

      final canTransition = character.currentState?.canTransitionTo(
        CharacterState.hit,
      );

      expect(canTransition, true);
    });
  });

  group('Hitstop System Tests', () {
    test('should trigger hitstop and return time scale 0', () {
      HitstopManager.triggerHitstop();

      final timeScale = HitstopManager.getTimeScale(0.016);

      expect(timeScale, 0.0);
      expect(HitstopManager.isInHitstop(), true);
    });

    test('should return to normal time scale after hitstop duration', () {
      HitstopManager.triggerHitstop();

      HitstopManager.getTimeScale(0.06);
      final timeScale = HitstopManager.getTimeScale(0.016);

      expect(timeScale, 1.0);
      expect(HitstopManager.isInHitstop(), false);
    });
  });

  group('Camera Shake System Tests', () {
    test('should trigger camera shake with intensity', () {
      CameraShakeManager.triggerShake(10.0);

      expect(CameraShakeManager.isShaking(), true);
    });

    test('should return shake offset during shake', () {
      CameraShakeManager.triggerShake(5.0);

      final offset = CameraShakeManager.getShakeOffset(0.016);

      expect(offset.x, isNot(0.0));
      expect(offset.y, isNot(0.0));
    });

    test('should stop shaking after duration', () {
      CameraShakeManager.triggerShake(5.0);

      CameraShakeManager.getShakeOffset(0.3);
      CameraShakeManager.getShakeOffset(0.016);

      expect(CameraShakeManager.isShaking(), false);
    });
  });

  group('Character Movement Tests', () {
    late Character character;

    setUp(() async {
      character = Character();
      character.position = Vector2(100, 400);
      await character.onLoad();
    });

    test('should move right when direction is right', () {
      character.direction = Direction.right;
      character.isMoving = true;
      character.changeState(CharacterState.run);

      character.update(0.1);

      expect(character.position.x, greaterThan(100));
    });

    test('should move left when direction is left', () {
      character.direction = Direction.left;
      character.isMoving = true;
      character.changeState(CharacterState.run);

      character.update(0.1);

      expect(character.position.x, lessThan(100));
    });

    test('should apply gravity when in air', () {
      character.isOnGround = false;
      character.velocity.y = 0;

      character.update(0.1);

      expect(character.velocity.y, greaterThan(0));
    });

    test('should stop at ground level', () {
      character.position.y = 600;
      character.velocity.y = 100;

      character.update(0.1);

      expect(character.position.y, 500);
      expect(character.velocity.y, 0);
      expect(character.isOnGround, true);
    });
  });

  group('Character Health Tests', () {
    late Character character;

    setUp(() async {
      character = Character();
      await character.onLoad();
    });

    test('should reduce health when taking damage', () {
      character.takeDamage(30);

      expect(character.health, 70);
    });

    test('should not reduce health below 0', () {
      character.takeDamage(150);

      expect(character.health, 0);
    });

    test('should reset health to max', () {
      character.takeDamage(50);
      character.reset();

      expect(character.health, 100);
    });
  });
}
