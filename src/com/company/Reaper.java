package com.company;
// lives again
// mystery drop (lore?)
// MYSTERY DROP IS ANCIENT SEED
// EAT SEED GO TO MARYS

public class Reaper extends Enemy{
    public Reaper(String name, int health, int attack, String description) {
        super(name, health, attack, description);
    }

    public void died(Player player, Floor floor){
        lore();
        Item item = new Item("Ancient seed", 0, "An ancient seed left behind from the future. Perhaps you should plant it?");
        player.defeatedMonster(item);
        System.out.println(name + " dropped " + item.getName());
        floor.addDeadEnemy(this);
    }

    public void lore(){
        System.out.println(italic + """
                
                You raise your sword against the cloaked reaper, ready to give the final blow.
                
                "Who.. are you?", you ask.
                For some reason, you feel a sense of deja vu, as if you've been here before, as if recognize cloaked reaper.
                Your head begins pounding again.
                
                "I am... insignificant. It is of no use for you to know who I am."
                The reaper breathes heavily, holding a hand against the wounds you've dealt.
                Blood is pouring out, and you both know there's not much time left before the last breath is taken.
                The reaper leans against the wall for support.
                
                After all those monsters you've defeated and all those floors you've fought through, you should be used to killing by now.
                But for some reason, you hesitate.
                
                Silence.
                
                The reaper shifts around, then takes an item out of the cloak's pocket. It glows a faint green colour.
                You raise your sword again, on guard as to what final trick will be played.
                
                "No need to be wary warrior, I do not wish to hurt you."
                
                "What do you wish then?"
                
                "... It does not matter what I wish for now."
                
                A somber silence falls between the two of you.
                
                "Brave warrior, may I ask something of you?"
                
                For some reason, you feel compelled to listen.
                
                "Please, take this item and plant it in the ground. That is all I ask of you. Treat it as a dying wish."
                
                You approach the reaper and take the glowing green item. It's very small and light, but you can sense it holds a strong life force.
                
                "What is this?"
                
                "It's an ancient seed from the future. Plant it in the ground, and let a new cycle begin."
                Before you can ask any more questions, the reaper's body slumps over. You feel a sense of sadness and loss, although you're not sure why.
                
                You slowly walk out of the tower, examining the item you received. It feels familiar to you. ALl of this, feels familiar to you.
                When you reach the fields, filled with wilted grass and invading weeds, you get to work and plant the seed.
                You feel a sense of relief and accomplishment. For some reason, you feel as if you've accomplished your purpose here.
                Finally, you drop your weapons and lie in the fields to sleep.
                """ + reset);
    }
}
