package com.aliberkaygedikoglu.bitirmeproje.di;

import com.aliberkaygedikoglu.bitirmeproje.data.repo.SepetDaoRepository;
import com.aliberkaygedikoglu.bitirmeproje.data.repo.YemeklerDaoRepository;
import com.aliberkaygedikoglu.bitirmeproje.retrofit.ApiUtils;
import com.aliberkaygedikoglu.bitirmeproje.retrofit.YemekDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public YemeklerDaoRepository provideYemeklerDaoRepository(YemekDao yDao){
        return new YemeklerDaoRepository(yDao);
    }
    @Provides
    @Singleton
    public YemekDao provideYemekDao(){
        return ApiUtils.getYemeklerDao();
    }






}
