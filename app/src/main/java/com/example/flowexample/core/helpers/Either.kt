package com.example.flowexample.core.helpers

import com.example.flowexample.core.failure.Failure
import com.example.flowexample.core.helpers.Either.Left
import com.example.flowexample.core.helpers.Either.Right

sealed class Either<out L, out R> {
    data class Left<out L>(val l: L) : Either<L, Nothing>()
    data class Right<out R>(val r: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    val leftValueOrNull get() = (this as? Left)?.l

    val rightValueOrNull get() = (this as? Right)?.r
}

fun <L> left(value: L): Left<L> = Left(value)

fun <R> right(value: R) = Right(value)


inline fun <L, R, T> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> = when (this) {
    is Left -> Left(l)
    is Right -> fn(r)
}


inline fun <L, R, T> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> = when (this) {
    is Left -> left(l)
    is Right -> right(fn(this.r))
}


inline fun <L, R, T> Either<L, R>.mapLeft(fn: (L) -> (T)): Either<T, R> = when (this) {
    is Left -> left(fn(this.l))
    is Right -> right(r)
}


inline fun <L, R, T> Either<L, R>.fold(left: (L) -> T, right: (R) -> T) = when (this) {
    is Left -> left(l)
    is Right -> right(r)
}

fun <T> T.toRight() = right(this)

fun <T : Failure> T.toLeft() = left(this)