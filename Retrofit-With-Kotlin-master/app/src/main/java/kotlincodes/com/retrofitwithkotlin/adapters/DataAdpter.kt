package kotlincodes.com.retrofitwithkotlin.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlincodes.com.retrofitwithkotlin.R
import kotlincodes.com.retrofitwithkotlin.model.DataModel
import kotlincodes.com.retrofitwithkotlin.photoviewer.PhotoViewerActivity

class DataAdpter(private var dataList: List<DataModel>, private val context: Context) : RecyclerView.Adapter<DataAdpter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_home, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = dataList.get(position)

        holder.tv_title.text = dataModel.title
        holder.tv_type.text = dataModel.type

        Glide.with(context)
                .load(dataModel.avatar_url)
                .into(holder.iv_image)

        holder.iv_image.setOnClickListener {
            val intent = Intent(context, PhotoViewerActivity::class.java)
            intent.putExtra("title", dataModel.title)
            intent.putExtra("avatar_url", dataModel.avatar_url)
            context.startActivity(intent)
        }

    }


    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        lateinit var tv_title: TextView
        lateinit var tv_type: TextView
        lateinit var iv_image: ImageView

        init {
            tv_title = itemLayoutView.findViewById(R.id.tv_title)
            tv_type = itemLayoutView.findViewById(R.id.tv_type)
            iv_image = itemLayoutView.findViewById(R.id.iv_image)
        }

    }

}