package com.example.filetree

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.unnamed.b.atv.model.TreeNode
import com.unnamed.b.atv.view.AndroidTreeView

class FileTreeItemHolder(
    context: Context
) : TreeNode.BaseNodeViewHolder<FileTreeItemHolder.FileTreeItem>(context) {

    override fun createNodeView(node: TreeNode?, value: FileTreeItem?): View =
        LayoutInflater.from(context).inflate(
            R.layout.item_file_tree,
            node?.parent?.viewHolder?.nodeItemsView,
            false
        ).apply {
            setOnClickListener {
                tView.toggleNode(node)
                Log.i("테스트", "toggle")
            }
        }

    class FileTreeItem(private val value: String)
}