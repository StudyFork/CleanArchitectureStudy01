package com.example.domain.datastructure

class Tree<T> {
    @Suppress("UNCHECKED_CAST")
    private val root: Node<T> = Node(Any() as T)

    private fun searchNode(target: T): Node<T>? {
        val queue = mutableListOf<Node<T>>()

        queue.addAll(root.childNodes)
        while (queue.isNotEmpty()) {
            queue.removeAt(0).also {
                if (it.element == target) {
                    queue.clear()
                    return it
                }
                queue.addAll(it.childNodes)
            }
        }
        return null
    }

    private fun searchNode(isParent: (T) -> Boolean): Node<T>? {
        val queue = mutableListOf<Node<T>>()

        queue.addAll(root.childNodes)
        while (queue.isNotEmpty()) {
            queue.removeAt(0).also {
                if (isParent(it.element)) {
                    queue.clear()
                    return it
                }
                queue.addAll(it.childNodes)
            }
        }
        return null
    }

    fun addChild(child: T, isParent: (T) -> Boolean): Boolean {
        searchNode(isParent)?.let {
            it.childNodes.add(Node(child))
            return true
        }
        return false
    }

    fun addChild(target: T?, child: T): Boolean {
        if (target == null) {
            root.childNodes.add(Node(child))
            return true
        }

        searchNode(target)?.let {
            it.childNodes.add(Node(child))
            return true
        }
        return false
    }

    fun clearChildNode(target: T) {
        searchNode(target)?.childNodes?.clear()
    }

    fun getRootChild(): List<T> {
        return root.childNodes.map(Node<T>::element)
    }

    fun getChild(target: T): List<T> {
        return searchNode(target)?.childNodes?.map(Node<T>::element) ?: emptyList()
    }

    fun getAllChild(target: T): List<T> {
        val queue = mutableListOf<Node<T>>()
        var index = 0

        queue.addAll(searchNode(target)?.childNodes ?: emptyList())
        while (index < queue.size) {
            queue.addAll(queue[index].childNodes)
            index++
        }

        return queue.map(Node<T>::element)
    }


    private class Node<T> constructor(
        val element: T
    ) {
        val childNodes: MutableList<Node<T>> = mutableListOf()
    }
}

