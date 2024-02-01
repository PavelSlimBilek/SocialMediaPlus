package com.example.socialmediaplusproject;

import com.example.socialmediaplusproject.models.Post;
import com.example.socialmediaplusproject.models.Uzer;
import com.example.socialmediaplusproject.repositories.PostRepo;
import com.example.socialmediaplusproject.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@RequiredArgsConstructor
public class SocialMediaPlusProjectApplication implements CommandLineRunner {

    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SocialMediaPlusProjectApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Uzer user = new Uzer("kokot", passwordEncoder.encode("kokot"));
        user.setFirstName("Pavel");
        user.setLastName("Bílek");
        user.setAbout("I created this mess...");
        userRepo.save(user);

        Uzer user1 = new Uzer("69_lover", passwordEncoder.encode("kokot")).setImage("/images/users/user69.png");
        Uzer user2 = new Uzer("uzr420", passwordEncoder.encode("kokot")).setImage("/images/users/user420.png");
        Uzer user3 = new Uzer("demonic-666", passwordEncoder.encode("kokot")).setImage("/images/users/user666.png");
        Uzer user4 = new Uzer("g04tNPC", passwordEncoder.encode("kokot")).setImage("/images/users/goatnpc.png");
        Uzer user5 = new Uzer("prdelka001", passwordEncoder.encode("kokot")).setImage("/images/users/prdelka.png");
        Uzer user6 = new Uzer("avionicsidiot", passwordEncoder.encode("kokot")).setImage("/images/users/avionicsidiot.png");

        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        userRepo.save(user4);
        userRepo.save(user5);
        userRepo.save(user6);

        Post post1 = new Post("Hello World!", "This is the very first post on this page");
        Post post9 = new Post("Bueeeeeee", "Bueeeeeeeeeeeeeeeeeeeeee bueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee bueeeeeeeeeeeeeeeeeeeeeeeeeeeeeee bueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        Post post2 = new Post("I was late daaaamnn..", "This is just the third post.. damn!!!");
        Post post3 = new Post("666 the Number of The Beast", "Check out the best album in the World!");
        Post post4 = new Post("LOVEEEE 69", "Is there anybody else loving 69 as much as I do?");
        Post post10 = new Post("Goat Simulator 3", "Did anyone hear anything about Goat Simulator3? Anyone please..");
        Post post5 = new Post("1993?", "Who is here in 1993?");
        Post post11 = new Post("Twich?", "this is twich?");
        Post post6 = new Post("American Idiot best album?", "Do you also believe that American Idiot is the best pop/punk/rock of all times?");
        Post post7 = new Post("EMIN3M nooBz!", "stfu those albums are bullshit!!");
        Post post8 = new Post("Have you heard about minecraft guyz?", "Minecraft is a new mindblowing sandbox game! Worth playing the shit out of it!");
        Post post12 = new Post("RUSSIAN fighters ARE flying PIECE of SHIT!", "Let's face that fanboys. Russian toys serve to feed the Devil in HElll!!........");

        user5.addPost(post11);
        post11.setAuthor(user5);
        user1.addPost(post1);
        post1.setAuthor(user1);
        user4.addPost(post10);
        post10.setAuthor(user4);
        user1.addPost(post4);
        post4.setAuthor(user1);
        user3.addPost(post3);
        post3.setAuthor(user3);
        user1.addPost(post6);
        post6.setAuthor(user1);
        user3.addPost(post8);
        post8.setAuthor(user3);
        user2.addPost(post2);
        post2.setAuthor(user2);
        user2.addPost(post5);
        post5.setAuthor(user2);
        user4.addPost(post9);
        post9.setAuthor(user4);
        user2.addPost(post7);
        post7.setAuthor(user2);
        user6.addPost(post12);
        post12.setAuthor(user6);

        postRepo.save(post1);
        postRepo.save(post6);
        postRepo.save(post3);
        postRepo.save(post2);
        postRepo.save(post9);
        postRepo.save(post8);
        postRepo.save(post11);
        postRepo.save(post4);
        postRepo.save(post7);
        postRepo.save(post10);
        postRepo.save(post5);
        postRepo.save(post12);

        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        userRepo.save(user4);
        userRepo.save(user5);
        userRepo.save(user6);
    }
}