package xmq.base.reps

open class BaseRepository<T : ILocalDataSource, R : IRemoteDataSource>(
    val local: T,
    val remote: R
) : IRepository

open class BaseRepositoryLocal<T : ILocalDataSource>(val local: T) : IRepository

open class BaseRepositoryRemote<T : IRemoteDataSource>(val remote: T) :
    IRepository

open class BaseRepositoryNothing() : IRepository