package org.example

import java.io.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit) {
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        val jobs = List(n) { launch { repeat(k) { action() } } }
        jobs.forEach { it.join() }
    }
    println("S-au efectuat ${n * k} operatii in $time ms")
}

val counter = AtomicInteger(0)

@DelicateCoroutinesApi
fun main() =
    runBlocking<Unit> {
        val channel = Channel<Int>()

        GlobalScope.launch {
            val f = File("counter.txt")

            f.writeText("")

            for (msg in channel) {
                f.appendText("${counter.incrementAndGet()}\n")
            }
        }

        GlobalScope.massiveRun { channel.send(1) }

        println("Gata ${counter.get()}")
    }