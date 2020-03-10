package com.worldsnas.starplayer.di

import com.worldsnas.starplayer.view.PlayerFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [AppComponent::class])
interface PlayerFragmentComponent {

    fun inject(playerFragment: PlayerFragment)

    @Component.Builder
    interface Builder {

        fun build(): PlayerFragmentComponent

        fun mainApplicationComponent(appComponent: AppComponent): Builder

        @BindsInstance
        fun playerFragment(playerFragment: PlayerFragment): Builder
    }
}