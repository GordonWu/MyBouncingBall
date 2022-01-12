package gordon.lab.mybouncingball

import android.content.res.Resources
import android.util.TypedValue
import java.util.*

object MisuUtils {
}

// Extensions
//
fun Int.dpToPx(): Int = (this * TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, Resources.getSystem().displayMetrics)).toInt()
fun ClosedRange<Int>.rand() = Random().nextInt((endInclusive + 1) - start) +  start
fun Float.dpToPx(): Int = (this * TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, Resources.getSystem().displayMetrics)).toInt()
fun Double.dpToPx(): Int = (this * TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, Resources.getSystem().displayMetrics)).toInt()