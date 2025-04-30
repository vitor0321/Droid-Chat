package com.example.droidchat.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.droidchat.data.mapper.UserResponseMapper.toUserList
import com.example.droidchat.data.model.request.PaginationParams
import com.example.droidchat.data.network.NetWorkDataSource
import com.example.droidchat.domain.model.User
import javax.inject.Inject

internal class UserPagingSource @Inject constructor(
    private val netWorkDataSource: NetWorkDataSource
) : PagingSource<Int, User>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, User> {
        return try {
            val offset = params.key ?: 0
            val response = netWorkDataSource.getUsers(
                paginationParams = PaginationParams(
                    offset = offset.toString(),
                    limit = params.loadSize.toString()
                )
            )
            return LoadResult.Page(
                data = response.toUserList(),
                prevKey = null,
                nextKey = if (response.hasMore) offset + params.loadSize else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(state.config.pageSize) ?: anchorPage?.nextKey?.minus(state.config.pageSize)
        }
    }
}