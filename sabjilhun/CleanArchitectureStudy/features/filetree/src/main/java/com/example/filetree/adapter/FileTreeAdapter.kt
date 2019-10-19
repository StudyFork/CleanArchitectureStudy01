package com.example.filetree.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.datastructure.Node
import com.example.domain.datastructure.Tree
import com.example.domain.entities.RepositoryFile
import com.example.filetree.R
import com.example.filetree.databinding.ItemFileTreeBinding

class FileTreeAdapter(var treeNode: Tree<RepositoryFile>) :
    RecyclerView.Adapter<FileTreeAdapter.FileViewHolder>() {
    private val flattenTree = mutableListOf<Node<RepositoryFile>>()
    var onFileClickListener: (() -> Unit)? = null

    init {
        flattenTree.add(treeNode.root)
    }

    fun expendFolder(repositoryFile: Node<RepositoryFile>) {
        if (treeNode.root != repositoryFile &&
            repositoryFile.element.type == RepositoryFile.FileType.FILE
        ) {
            return
        }
        flattenTree.addAll(flattenTree.indexOf(repositoryFile), repositoryFile.childNodes)
        notifyDataSetChanged()
    }

    private fun foldFolder(index: Int, repositoryFile: Node<RepositoryFile>) {
        if (repositoryFile.element.type == RepositoryFile.FileType.FILE) {
            return
        }
        flattenTree.removeAll(repositoryFile.childNodes)
        notifyDataSetChanged()
    }

    fun foldAll() {
        flattenTree.clear()
        flattenTree.addAll(treeNode.root.childNodes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = FileViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_file_tree, parent, false
        ).apply {
            setOnClickListener { onFileClickListener?.let { it() } }
        }
    )

    override fun getItemCount(): Int = (flattenTree.size - 1)

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        // root 제외
        holder.binding.repositoryFile = flattenTree[position].element
    }

    class FileViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemFileTreeBinding =
            requireNotNull(DataBindingUtil.bind(itemView))
    }
}