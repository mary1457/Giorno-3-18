package mariapiabaldoin.Giorno_3.controllers;


import mariapiabaldoin.Giorno_3.entities.Autore;
import mariapiabaldoin.Giorno_3.payloads.NewAutorePayload;
import mariapiabaldoin.Giorno_3.services.AutoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autori")
public class AutoriController {
    @Autowired
    private AutoriService autoriService;


    @GetMapping
    public Page<Autore> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {

        return this.autoriService.findAll(page, size, sortBy);
    }


    @GetMapping("/{autoreId}")
    public Autore findAutoreById(@PathVariable long autoreId) {
        return this.autoriService.findById(autoreId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autore createAutore(@RequestBody NewAutorePayload body) {
        return this.autoriService.save(body);
    }


    @PutMapping("/{autoreId}")
    public Autore findAutoreByIdAndUpdate(@PathVariable long autoreId, @RequestBody NewAutorePayload body) {
        return this.autoriService.findByIdAndUpdate(autoreId, body);
    }


    @DeleteMapping("/{autoreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAutoreByIdAndDelete(@PathVariable long autoreId) {
        this.autoriService.findByIdAndDelete(autoreId);
    }


}
