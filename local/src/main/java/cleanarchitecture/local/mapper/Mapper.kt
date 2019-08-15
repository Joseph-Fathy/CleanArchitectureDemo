package cleanarchitecture.local.mapper

/*For converting data model from Data Layer format to Local Layer format */
interface Mapper<T, E> {

    fun from(e: E): T

    fun to(t: T): E

}
