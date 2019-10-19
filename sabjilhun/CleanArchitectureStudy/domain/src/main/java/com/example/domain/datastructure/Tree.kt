package com.example.domain.datastructure

class Tree<T> {
    private val queue = mutableListOf<Node<T>>()
    @Suppress("UNCHECKED_CAST")
    val root: Node<T> = Node(Any() as T)

    private fun searchNode(target: Node<T>): Node<T>? {
        if (root == target) {
            return root
        }

        queue.addAll(root.childNodes)
        while (queue.isNotEmpty()) {
            queue.removeAt(0).also {
                if (it == target) {
                    queue.clear()
                    return it
                }
                queue.addAll(it.childNodes)
            }
        }
        return null
    }

    fun depthForEach(target: Node<T>, consumer: (Node<T>) -> Unit) {
        if (root == target) {
            return
        }

        queue.addAll(root.childNodes)
        while (queue.isNotEmpty()) {
            queue.removeAt(0).also {
                if (it == target) {
                    queue.clear()
                    return
                }
                consumer(it)
                queue.addAll(it.childNodes)
            }
        }
        return
    }

    fun setChildNodes(target: Node<T>, childNodes: List<Node<T>>): Boolean {
        searchNode(target)?.apply {
            replaceAll(childNodes)
            return true
        }
        return false
    }
}

class Node<T> constructor(
    val element: T
) {
    val childNodes: MutableList<Node<T>> = mutableListOf()

    fun replaceAll(childNodes: List<Node<T>>) {
        this.childNodes.apply {
            clear()
            addAll(childNodes)
        }
    }
}