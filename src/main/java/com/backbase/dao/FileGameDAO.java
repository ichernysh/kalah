package com.backbase.dao;

import com.backbase.exceptions.APIException;
import com.backbase.game.State;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class FileGameDAO extends GameDAO {

    public FileGameDAO() {
    }

    @Override
    public void storeState(State state) {
        try (FileOutputStream f = new FileOutputStream(new File("state.txt"));
             ObjectOutputStream o = new ObjectOutputStream(f)) {

            o.writeObject(state);
        } catch (IOException e) {
            System.out.println("Enable to store state. - " + e);
        }
    }

    @Override
    public Optional<State> restoreState() {
        State state;
        try (FileInputStream fi = new FileInputStream(new File("state.txt"));
             ObjectInputStream oi = new ObjectInputStream(fi);) {
            state = (State) oi.readObject();
            return Optional.ofNullable(state);
        } catch (IOException | ClassNotFoundException e) {
            throw new APIException("Enable to restore state.", e);
        }
    }

    @Override
    public void deleteState() {
        try {
            File file = new File("state.txt");
            Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            System.out.println("Enable to delete state. - " + e);
        }
    }
}
