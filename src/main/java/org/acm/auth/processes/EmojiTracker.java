package org.acm.auth.processes;


import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;

// Custom emoji format: <{emoji.type}:{emoji.name}:{emoji.id}>
// {emoji.type}: Animated: "a", Not Animated: ""
// {emoji.name}: Name of the emote, eg: "righteyes"
// {emoji.id}: Unique emoji ID
// Typical Custom Emoji return message: MR:(M:({channel.ID}}) / RE:{emoji.name}}({emoji.id}}))
//
//Reaction Added! Test ?
//        MR:(M:(822403672993562634) / RE:U+1f44d)
//        MR:(M:(822403672993562634) / RE:kota_peripou(785484646183731211))

public class EmojiTracker {


    public static void add(GuildMessageReactionAddEvent event) {

        System.out.println("Reaction Added!");
        MessageReaction.ReactionEmote reaction = event.getReactionEmote();
        Emoji emoji = new Emoji(reaction);

    }

    public static void remove(GuildMessageReactionRemoveEvent event) {

        System.out.println("Reaction Removed!");
        MessageReaction.ReactionEmote reaction = event.getReactionEmote();
        Emoji emoji = new Emoji(reaction);

    }

    private static class Emoji {
        private final MessageReaction.ReactionEmote reaction;
        public String raw = "";

        Emoji(MessageReaction.ReactionEmote reaction) {
            this.reaction = reaction;
            if (reaction.isEmoji()){
                this.raw = reaction.getEmoji();
            }else{
                this.raw = reaction.getEmote().getAsMention();
            }
        }
    }
}
