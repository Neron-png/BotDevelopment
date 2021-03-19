package org.acm.auth.processes;


import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String reaction = event.getReaction().toString();
        Emoji emoji = new Emoji(reaction);


    }

    public static void remove(GuildMessageReactionRemoveEvent event) {

        System.out.println("Reaction Added!");
        String reaction = event.getReaction().toString();
        Emoji emoji = new Emoji(reaction);

    }

    private static class Emoji {
        private final String reaction;
        public String id;
        public String name;
        public String type = ""; // Currently there is no way to detect it, but it doesn't matter much
        public String formatted = "";

        Emoji(String reaction) {
            this.reaction = reaction;

            if (isCustom()) {
                this.id = find(reaction, "(?<=\\()([0-9]*\\n?)(?=\\)\\))");  // Regex: find numbers between "(" and "))"
                this.name = find(reaction, "(?<=RE:)(.*\\n?)(?=\\()");       // Regex: find anything between "RE:" and "("
                this.formatted = String.format("<%s:%s:%s>", type, name, id);
            } else {
                this.name = null;
                this.id = find(reaction, "(?<=RE:U\\+)(.*\\n?)(?=\\))");     // Regex: find anything between "RE:" and ")"
                int ch = Integer.decode(formatted);
                this.formatted = String.format("\u1dca%s", id);
            }
        }

        public boolean isCustom() {
            //Custom Emoji don't have the "+" symbol
            return !reaction.contains("U+");
        }


        private String find(String input, String regex) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                return matcher.group(0);
            }
            return null;
        }
    }
}
