package gong.team.data.mapper

interface BaseMapper<in T , out R> {
    fun mapFrom(from: T): R
}