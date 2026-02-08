import 'dart:math' as math;
import 'package:flame/camera.dart';
import 'package:flame/components.dart';

class HitstopManager {
  static double _timeScale = 1.0;
  static double _hitstopTimer = 0.0;
  static const double hitstopDuration = 0.05;

  static void triggerHitstop() {
    _hitstopTimer = hitstopDuration;
    _timeScale = 0.0;
  }

  static double getTimeScale(double dt) {
    if (_hitstopTimer > 0) {
      _hitstopTimer -= dt;
      return 0.0;
    } else {
      _timeScale = 1.0;
      return _timeScale;
    }
  }

  static bool isInHitstop() {
    return _hitstopTimer > 0;
  }
}

class CameraShakeManager {
  static Vector2 _shakeOffset = Vector2.zero();
  static double _shakeTimer = 0.0;
  static double _shakeIntensity = 0.0;
  static const double shakeDuration = 0.3;

  static void triggerShake(double intensity) {
    _shakeIntensity = intensity;
    _shakeTimer = shakeDuration;
  }

  static Vector2 getShakeOffset(double dt) {
    if (_shakeTimer > 0) {
      _shakeTimer -= dt;

      final progress = _shakeTimer / shakeDuration;
      final decay = 1.0 - progress;

      _shakeOffset.x =
          (math.Random().nextDouble() * 2 - 1) * _shakeIntensity * decay;
      _shakeOffset.y =
          (math.Random().nextDouble() * 2 - 1) * _shakeIntensity * decay;

      return _shakeOffset;
    } else {
      _shakeOffset.setZero();
      return _shakeOffset;
    }
  }

  static bool isShaking() {
    return _shakeTimer > 0;
  }
}

class ImpactEffectManager extends Component {
  final List<ImpactEffect> _effects = [];

  @override
  void update(double dt) {
    for (int i = _effects.length - 1; i >= 0; i--) {
      _effects[i].update(dt);
      if (_effects[i].isFinished) {
        _effects[i].onComplete();
        _effects.removeAt(i);
      }
    }
  }

  void addEffect(ImpactEffect effect) {
    _effects.add(effect);
    add(effect);
  }

  void clearEffects() {
    for (final effect in _effects) {
      effect.onComplete();
      remove(effect);
    }
    _effects.clear();
  }
}

class ImpactEffect extends PositionComponent {
  double _timer = 0.0;
  double _duration;
  final void Function() onComplete;

  ImpactEffect({
    required Vector2 position,
    required double duration,
    required this.onComplete,
  }) : _duration = duration,
       super(position: position);

  @override
  void update(double dt) {
    _timer += dt;
    if (_timer >= _duration) {
      removeFromParent();
    }
  }

  bool get isFinished => _timer >= _duration;
}
