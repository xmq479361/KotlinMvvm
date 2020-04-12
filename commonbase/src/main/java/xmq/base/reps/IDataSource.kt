package xmq.base.reps

import xmq.base.ext.ICoroutine

interface IDataSource: ICoroutine {
}

interface ILocalDataSource: IDataSource
interface IRemoteDataSource: IDataSource