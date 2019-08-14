package com.theplanet.cleanarchitecturecourse.mapper

/*For converting data model from Domain Layer format to Presentation Layer format */
interface Mapper<T, E> {

    fun from(e: E): T

    fun to(t: T): E

}
