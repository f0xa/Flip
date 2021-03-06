package cc.foxa.flip.shared.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import cc.foxa.flip.shared.data.CardRepository
import cc.foxa.flip.shared.data.pref.PreferenceStorage
import cc.foxa.flip.shared.data.pref.SharedPreferenceStorage
import cc.foxa.flip.shared.data.source.AppDatabase
import cc.foxa.flip.shared.data.source.CardTagDao
import javax.inject.Singleton

@Module
object SharedModule{
    @Provides
    @Singleton
    fun provideAppDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "flip-db").build()

    @Provides
    @Singleton
    fun provideCardTagDao(appDatabase: AppDatabase) = appDatabase.cardTagDao()

    @Provides
    @Singleton
    fun provideRuleFilterDao(appDatabase: AppDatabase) = appDatabase.ruleFilterDao()

    @Provides
    @Singleton
    fun provideCardRepository(cardTagDao: CardTagDao) = CardRepository(cardTagDao)

    @Provides
    @Singleton
    fun providePreferenceStorage(context: Context): PreferenceStorage
        = SharedPreferenceStorage(context)
}