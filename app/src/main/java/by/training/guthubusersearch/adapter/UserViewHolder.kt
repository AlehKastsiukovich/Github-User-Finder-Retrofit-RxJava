package by.training.guthubusersearch.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.training.guthubusersearch.R
import by.training.guthubusersearch.data.User
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_item.view.profileImage
import kotlinx.android.synthetic.main.user_item.view.userLogin

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val userImage = itemView.profileImage
    private val userLogin = itemView.userLogin

    fun bind(user: User) {
        Glide.with(itemView.context)
            .load(user.avatar_url)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_face_24)
            .into(userImage)
        userLogin.text = user.login
    }
}
