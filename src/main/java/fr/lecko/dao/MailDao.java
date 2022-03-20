package fr.lecko.dao;

import fr.lecko.contract.save_microsoft_graph.dto.Mail;

import java.util.List;

public interface MailDao {

    /**
     * Insert mails in database
     * @param mails a list of mails
     */
    void insertMails(List<Mail> mails);

    /**
     * Return mail from database
     * @return list of mail
     */
    List<Mail> getMails();
}
