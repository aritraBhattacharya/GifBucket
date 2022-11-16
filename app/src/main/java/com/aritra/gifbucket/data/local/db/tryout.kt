//package com.aritra.gifbucket.data.local.db
//
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ensureActive
//import kotlinx.coroutines.launch
//
//class OuterClass() {
//    //---------------------------------OUTER--------------------------------------------------------------
//    private var strOuterPrivate = "Outer class private string"
//    var strOuterPublic = "Outer class public string"
//    val myLambdaSumFun: (a:Int,b:Int)->Int = {x,y->x+y}
//    val myLambdaSumFun = {x:Int,y:Int->x+y}
//
//    fun login(userName:String): String{
//        fun validate(name:String) = name.isNotEmpty()
//
//        if(validate(userName)) return "login success"
//
//        val job = CoroutineScope(Dispatchers.Main).launch {
//            ensureActive()
//        }
//
//    }
//
//    fun publicOuterMethod() = println("public outer method")
//    private fun privateOuterMethod() = println("private outer method")
//
//    fun checkAccessToInnerClass(){
//        InnerClass().innerClassPublicString
//        InnerClass().innerClassPrivateString // no access to private inner class property
//        InnerClass().innerPublicFun()
//        InnerClass().privateOuterMethod() // no access to private inner class fun
//    }
//
//    fun checkAccessToNestedClass(){
//        NestedClass().nestedClassPublicString
//        NestedClass().nestedClassPrivateString // no access to private nested class property
//        NestedClass().nestedPublicFun()
//        NestedClass().nestedPrivateFun() // no access to private nested class fun
//
//    }
//    //---------------------------------------INNER--------------------------------------------------------
//
//    inner class InnerClass {
//        var s1Inner = strOuterPrivate // can access ouer public and private property and fun
//        var s2Inner = strOuterPublic
//
//        fun TryToAccessOuerFunsFromInner(){
//            publicOuterMethod()
//            privateOuterMethod()
//        }
//
//        var innerClassPublicString = "inner class public string"
//        private var innerClassPrivateString = "inner class private string"
//        fun innerPublicFun()="innerPublicFun"
//        private fun innerPrivateFun()="innerPrivateFun"
//    }
//    //---------------------------------------NESTED--------------------------------------------------------
//
//    class NestedClass{
//        var s1Nested = strOuterPrivate // cant access public member of outer class
//        var s2Nested = strOuterPublic // cant access private member of outer class
//
//        fun TryToAccessOuerFunsFromNested(){
//            publicOuterMethod() // cant access public fun of outer class
//            privateOuterMethod()// cant access private fun of outer class
//        }
//
//        var nestedClassPublicString = "nested class public string"
//        private var nestedClassPrivateString = "nested class private string"
//
//
//        fun nestedPublicFun() = println("say Hello From Nested Class")
//        private fun nestedPrivateFun() = println("say Hello From Nested Class")
//    }
//
//    class Single private constructor(){
//        companion object{
//            @Volatile
//            private var single:Single?=null
//            fun getInstance()= run {
//                if(single!=null) single
//                else{
//                    synchronized(Single){
//                        if(single==null){
//                            single = Single()
//                        }
//                        return@synchronized single
//                    }
//                }
//            }
//        }
//    }
//    //-----------------------------------------------------------------------------------------------
//
//    fun main(args: Array<String>) {
//        OuterClass.InnerClass() // Not possible without outer class instance
//        OuterClass().InnerClass().s1Inner
//        OuterClass.NestedClass().s1Nested // no need of Outer class instance
//
//
//    }
//}