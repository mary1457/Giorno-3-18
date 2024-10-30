package mariapiabaldoin.Giorno_3.services;

import mariapiabaldoin.Giorno_3.entities.Autore;
import mariapiabaldoin.Giorno_3.exceptions.BadRequestException;
import mariapiabaldoin.Giorno_3.exceptions.NotFoundException;
import mariapiabaldoin.Giorno_3.payloads.NewAutorePayload;
import mariapiabaldoin.Giorno_3.repositories.AutoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AutoriService {

    @Autowired
    private AutoriRepository autoriRepository;

    public Autore save(NewAutorePayload body) {

        this.autoriRepository.findByEmail(body.getEmail()).ifPresent(

                user -> {
                    throw new BadRequestException("Email " + body.getEmail() + " già in uso!");
                }
        );


        Autore newAutore = new Autore(body.getNome(), body.getCognome(), body.getEmail(), body.getDataNascita());


        String avatarUrl = "https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome();
        newAutore.setAvatar(avatarUrl);
        return this.autoriRepository.save(newAutore);
    }

    public Page<Autore> findAll(int page, int size, String sortBy) {
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return this.autoriRepository.findAll(pageable);
    }

    public Autore findById(long autoreId) {
        return this.autoriRepository.findById(autoreId).orElseThrow(() -> new NotFoundException(autoreId));
    }

    public Autore findByIdAndUpdate(long autoreId, NewAutorePayload body) {

        Autore found = this.findById(autoreId);


        if (!found.getEmail().equals(body.getEmail())) {
            this.autoriRepository.findByEmail(body.getEmail()).ifPresent(

                    user -> {
                        throw new BadRequestException("Email " + body.getEmail() + " già in uso!");
                    }
            );
        }


        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        found.setDataNascita(body.getDataNascita());
        String avatarUrl = "https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome();
        found.setAvatar(avatarUrl);


        return this.autoriRepository.save(found);
    }

    public void findByIdAndDelete(long autoreId) {
        Autore found = this.findById(autoreId);
        this.autoriRepository.delete(found);
    }
}
