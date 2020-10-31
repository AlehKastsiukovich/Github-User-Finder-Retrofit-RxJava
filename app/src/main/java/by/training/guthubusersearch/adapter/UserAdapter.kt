package by.training.guthubusersearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.training.guthubusersearch.R
import by.training.guthubusersearch.data.User

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private var userList = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun update(users: List<User>) {
        userList.addAll(users)
        notifyDataSetChanged()
    }

    fun clear() {
        userList.clear()
        notifyDataSetChanged()
    }
}
