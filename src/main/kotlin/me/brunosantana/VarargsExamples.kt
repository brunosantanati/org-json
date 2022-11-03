package me.brunosantana

//https://stackoverflow.com/questions/44239869/whats-the-kotlin-equivalent-of-javas-string
//https://www.techiedelight.com/add-new-element-array-kotlin/

//Varargs and Spread Operator in Kotlin
//https://www.baeldung.com/kotlin/varargs-spread-operator

fun main() {
    test("item1", "item2", "item3")

    val a = arrayOf("item4", "item5", "item6")
    test(*a)

    var b = arrayOf<String>()
    b = b.plus("item7")
    b = b.plus("item8")
    b = b.plus("item9")
    test(*b)
}

fun test(vararg names: String){
    names.forEach { println(it) }
}