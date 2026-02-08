import 'dart:ui';
import 'package:flame/game.dart';
import 'package:flame_forge2d/flame_forge2d.dart';
import 'package:flame_audio/flame_audio.dart';
import 'character.dart';
import 'impact_system.dart';

class DnfGame extends FlameGame with HasCollisionDetection {
  late Character player;
  late ImpactEffectManager impactEffectManager;
  Vector2 cameraPosition = Vector2.zero();

  @override
  Color backgroundColor() => const Color(0xFF1a1a2a);

  @override
  Future<void> onLoad() async {
    await super.onLoad();

    final world = Forge2DWorld(gravity: Vector2(0, 800));
    add(world);

    impactEffectManager = ImpactEffectManager();
    add(impactEffectManager);

    player = Character();
    player.position = Vector2(100, 400);
    add(player);

    await FlameAudio.audioCache.loadAll(['attack.mp3', 'hit.mp3', 'jump.mp3']);
  }

  @override
  void update(double dt) {
    super.update(dt);

    final timeScale = HitstopManager.getTimeScale(dt);
    final scaledDt = dt * timeScale;

    player.update(scaledDt);
    impactEffectManager.update(dt);

    cameraPosition = player.position;

    if (CameraShakeManager.isShaking()) {
      final shakeOffset = CameraShakeManager.getShakeOffset(dt);
      cameraPosition = player.position - shakeOffset;
    }
  }

  @override
  void render(Canvas canvas) {
    super.render(canvas);

    if (HitstopManager.isInHitstop()) {
      final paint = Paint()..color = const Color(0xFFFF0000).withOpacity(0.1);
      canvas.drawRect(Rect.fromLTWH(0, 0, size.x, size.y), paint);
    }
  }

  void onPlayerAttack() {
    HitstopManager.triggerHitstop();
    CameraShakeManager.triggerShake(5.0);

    FlameAudio.play('attack.mp3');

    final effect = ImpactEffect(
      position: player.position.clone()..y -= 30,
      duration: 0.2,
      onComplete: () {},
    );
    impactEffectManager.addEffect(effect);
  }

  void onPlayerHit() {
    HitstopManager.triggerHitstop();
    CameraShakeManager.triggerShake(10.0);

    FlameAudio.play('hit.mp3');

    final effect = ImpactEffect(
      position: player.position.clone(),
      duration: 0.3,
      onComplete: () {},
    );
    impactEffectManager.addEffect(effect);
  }

  void onPlayerJump() {
    FlameAudio.play('jump.mp3');
  }
}
