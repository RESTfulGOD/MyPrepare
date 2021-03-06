package com.example.myprepare.view_test

import android.animation.*
import android.graphics.PointF
import android.os.Bundle
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import com.example.myprepare.dp
import com.example.myprepare.view.CameraView
import com.example.myprepare.view.CircleView
import com.example.myprepare.view.PointFView

class AnimationActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_animation)

    /*animation_image_view.animate()
        .translationX(200.dp)
        .translationY(100.dp)
        .alpha(0.5f)
        .scaleX(2f)
        .scaleY(2f)
        .rotation(30f)
        .startDelay = 1000*/

    val circleView: CircleView = findViewById(R.id.animation_circle_view)

    val animator: ValueAnimator = ObjectAnimator.ofFloat(circleView, "radius", 10.dp)
    animator.startDelay = 1000
    animator.interpolator = AnticipateOvershootInterpolator()
    animator.repeatMode = ValueAnimator.REVERSE
    animator.repeatCount = ValueAnimator.INFINITE
    animator.start()
    /* val bottomFlipAnimator = ObjectAnimator.ofFloat(animation_camera_view, "bottomFlip", 40f)
    bottomFlipAnimator.startDelay = 1000
    bottomFlipAnimator.duration = 1000
    //    bottomFlipAnimator.start()

    val topFlipAnimation = ObjectAnimator.ofFloat(animation_camera_view, "topFlip", -40f)
    topFlipAnimation.duration = 1000
    //    topFlipAnimation.start()

    val flipRotationAnimation = ObjectAnimator.ofFloat(animation_camera_view, "flipRotation", 120f)
    flipRotationAnimation.startDelay = 1000
    flipRotationAnimation.duration = 1000
    //    flipRotationAnimation.start()

    val animatorSet = AnimatorSet()
    animatorSet.playSequentially(topFlipAnimation, bottomFlipAnimator, flipRotationAnimation)
    animatorSet.startDelay = 1000
    animatorSet.start()*/

    // cameraView的动画
    val topFlipHolder = PropertyValuesHolder.ofFloat("topFlip", -40f)
    val bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip", 40f)
    val flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation", -120f)

    val cameraView: CameraView = findViewById(R.id.animation_camera_view)

    val holderAnimator = ObjectAnimator.ofPropertyValuesHolder(
      cameraView,
      topFlipHolder,
      bottomFlipHolder,
      flipRotationHolder
    )
    holderAnimator.startDelay = 1000
    holderAnimator.duration = 1000
    holderAnimator.start()

    // 图片的动画
    val length = (-200).dp

    val keyframe1: Keyframe = Keyframe.ofFloat(0f, 0f)
    val keyframe2: Keyframe = Keyframe.ofFloat(0.4f, 0.4f * length)
    val keyframe3: Keyframe = Keyframe.ofFloat(0.8f, 0.6f * length)
    val keyframe4: Keyframe = Keyframe.ofFloat(1f, length)

    val imageView: ImageView = findViewById(R.id.animation_image_view)

    val keyframesHolder =
      PropertyValuesHolder.ofKeyframe("translationX", keyframe1, keyframe2, keyframe3, keyframe4)
    val imageAnimator = ObjectAnimator.ofPropertyValuesHolder(imageView, keyframesHolder)
    imageAnimator.startDelay = 1000
    imageAnimator.duration = 1000
    imageAnimator.repeatCount = ValueAnimator.INFINITE
    imageAnimator.repeatMode = ValueAnimator.REVERSE
    imageAnimator.start()

    val point: PointFView = findViewById(R.id.animation_point_f)

    // 点的动画
    val pointFAnimator = ObjectAnimator.ofObject(
      point,
      "point",
      PointFEvaluator(),
      PointF(100.dp, 300.dp)
    )
    pointFAnimator.startDelay = 1000
    pointFAnimator.duration = 1000
    pointFAnimator.start()
  }

  class PointFEvaluator : TypeEvaluator<PointF> {

    override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
      val startX: Float = startValue.x
      val startY: Float = startValue.y

      val endX: Float = endValue.x
      val endY: Float = endValue.y

      val currentX = startX + fraction * (endX - startX)
      val currentY = startY + fraction * (endY - startY)

      return PointF(currentX, currentY)
    }

  }
}