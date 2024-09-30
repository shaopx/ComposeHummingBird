package com.spx.hummingbird.di

import com.spx.hummingbird.modules.news.datasource.NewsDataSource
import com.spx.hummingbird.modules.news.datasource.NewsDataSourceImpl
import com.spx.hummingbird.modules.news.repository.NewsDetailRepository
import com.spx.hummingbird.modules.news.repository.NewsDetailRepositoryImpl
import com.spx.hummingbird.modules.posts.datasource.PostsDataSource
import com.spx.hummingbird.modules.posts.datasource.PostsDataSourceImpl
import com.spx.hummingbird.modules.posts.repository.PostsRepository
import com.spx.hummingbird.modules.posts.repository.PostsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsNewsDataSource(
        newsDataSource: NewsDataSourceImpl,
    ): NewsDataSource

    @Binds
    internal abstract fun bindsPostsDataSource(
        postsDataSource: PostsDataSourceImpl,
    ): PostsDataSource

    @Binds
    internal abstract fun bindsPostsRepository(
        newsRepository: PostsRepositoryImpl,
    ): PostsRepository

    @Binds
    internal abstract fun bindsVideoNewsDetailRepository(
        repository: NewsDetailRepositoryImpl,
    ): NewsDetailRepository

//    @Binds
//    internal abstract fun bindsNewsPageSourceFactory(
//        pageSourceFactory: DefaultNewsPageSourceFactory,
//    ): NewsPageSourceFactory

}
