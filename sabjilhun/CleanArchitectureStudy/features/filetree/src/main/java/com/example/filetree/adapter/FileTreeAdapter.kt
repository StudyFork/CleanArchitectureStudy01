package com.example.filetree.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.datastructure.Tree
import com.example.domain.entities.RepositoryFile
import com.example.filetree.R
import com.example.filetree.databinding.ItemFileTreeBinding

class FileTreeAdapter :
    RecyclerView.Adapter<FileTreeAdapter.FileViewHolder>() {
    private val flattenTree = mutableListOf<FileDataHolder<RepositoryFile>>()
    var fileTree: Tree<RepositoryFile>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = FileViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_file_tree, parent, false
        )
    ).also { viewHolder ->
        viewHolder.binding.root.setOnClickListener {
            val fileDataHolder = flattenTree[viewHolder.adapterPosition]

            if (fileDataHolder.data.type == RepositoryFile.FileType.FILE) {
                return@setOnClickListener
            }

            if (fileDataHolder.isExpended) {
                foldFolder(fileDataHolder)
            } else {
                expendFolder(viewHolder.adapterPosition + 1, fileDataHolder)
            }
        }
    }

    override fun getItemCount(): Int = flattenTree.size

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val fileDataHolder = flattenTree[position]
        holder.binding.repositoryFile = fileDataHolder.data

        if (fileDataHolder.data.type == RepositoryFile.FileType.FILE) {
            holder.binding.ivFileIcon.setImageResource(R.drawable.ic_file_primary_24dp)
        } else {
            if (fileDataHolder.isExpended) {
                holder.binding.ivFileIcon.setImageResource(R.drawable.ic_folder_open_primary_24dp)
            } else {
                holder.binding.ivFileIcon.setImageResource(R.drawable.ic_folder_primary_24dp)
            }
        }

        holder.binding.root.setPadding(
            fileDataHolder.depth * PADDING_FOR_DEPTH,
            0, 0, 0
        )
    }

    private fun expendFolder(
        index: Int,
        fileDataHolder: FileDataHolder<RepositoryFile>
    ) {
        flattenTree.addAll(
            index,
            fileTree?.getChild(
                fileDataHolder.data
            )?.map {
                FileDataHolder(it, fileDataHolder.depth + 1, false)
            } ?: emptyList()
        )
        fileDataHolder.isExpended = true
        notifyDataSetChanged()
    }

    private fun foldFolder(fileDataHolder: FileDataHolder<RepositoryFile>) {
        val childFileList = fileTree?.getAllChild(fileDataHolder.data) ?: emptyList()
        flattenTree.removeAll { childFileList.contains(it.data) }
        fileDataHolder.isExpended = false
        notifyDataSetChanged()
    }

    fun updateFileTree(fileTree: Tree<RepositoryFile>) {
        this.fileTree = fileTree
        flattenTree.clear()
        flattenTree.addAll(fileTree.getRootChild().map {
            FileDataHolder(it, 1, false)
        })
        notifyDataSetChanged()
    }

    class FileViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemFileTreeBinding =
            requireNotNull(DataBindingUtil.bind(itemView))
    }

    private class FileDataHolder<T> constructor(
        val data: T,
        val depth: Int,
        var isExpended: Boolean
    )

    companion object {
        private const val PADDING_FOR_DEPTH = 40
    }
}