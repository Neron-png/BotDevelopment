package org.acm.auth.managers;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.acm.auth.processes.EmojiTracker;
import org.jetbrains.annotations.NotNull;

public class ReactionManager extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {

        if (event.getUser().isBot())
            return;

        EmojiTracker.add(event);
    }

    @Override
    public void onGuildMessageReactionRemove(@NotNull GuildMessageReactionRemoveEvent event) {

        if (event.getUser().isBot())
            return;

        EmojiTracker.remove(event);
    }
}
