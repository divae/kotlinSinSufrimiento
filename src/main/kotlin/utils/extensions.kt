package utils

fun <E> MutableSet<E>.update(element:E) = this.remove(element) && this.add(element)
