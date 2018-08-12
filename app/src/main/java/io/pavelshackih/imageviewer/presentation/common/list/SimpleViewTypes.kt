package io.pavelshackih.imageviewer.presentation.common.list

class SimpleViewTypes(block: Builder.() -> Unit) : ViewTypes {

    private val bindings: List<Pair<Class<*>, Int>>

    init {
        val builder = Builder()
        builder.block()
        bindings = builder.list
    }

    override fun getLayoutId(viewType: Int): Int {
        return bindings[viewType].second
    }

    override fun getViewTypeBy(clazz: Class<*>): Int {
        return bindings.indexOfFirst { it.first == clazz }
    }

    class Builder {

        val list = arrayListOf<Pair<Class<*>, Int>>()

        fun bind(pair: Pair<Class<*>, Int>) {
            list.add(pair)
        }
    }
}