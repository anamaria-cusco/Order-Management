package bll;

import bll.validators.ClientValidations;
import dao.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientBLL {
    private final ClientDAO clientDAO;

    public ClientBLL(){
        clientDAO=new ClientDAO();

    }

    /**
     * Metoda de validare a datelor unui client
     * @param client - clientul a carui date se valideaza
     * @return - o valoare intreaga corespunzatoare cu diferite cazuri de esec sau succes
     */
    private int validateClientData(Client client){
        ClientValidations clientValidations=new ClientValidations();
        if(!clientValidations.isValidName(client.getFirstName())){
            return 1;
        }
        if(!clientValidations.isValidName(client.getLastName())){
            return 2;
        }

        if(!clientValidations.isValidPhoneNumber(client.getPhone_number())){
            return 3;
        }

        if(!clientValidations.isValidEmailAddress(client.getEmail())){
            return 4;
        }
        return 0;

    }

    /**
     * Metoda de adaugare a unui client
     * @param client -clientul de inserat
     * @return - valoare intreaga ce resprezinta un succes/esec
     */
    public int addClient(Client client){


        if(clientDAO.findClientBySpecifiedFields(client)!=null){ //if client already exists
            return -1;
        }
        int valid = validateClientData(client);
        if(valid == 0) {
            clientDAO.insert(client);
        }
        else{
            return valid;
        }

        return 0;

    }

    /**
     * Metoda de editare a unui client
     * @param client - clientul a carui date urmeaza sunt actualizate
     * @return - valoare intreaga corespunzatoare cu un cod de esec/succes
     */
    public int editClient(Client client){

        int valid = validateClientData(client);
        if(valid == 0) {
            clientDAO.update(client);
        }
        else{
            return valid;
        }

        return 0;

    }

    /**
     * Metoda de stergere a unui client
     * @param id - id-ul clientului care se doreste a fi sters
     * @return - true/false in caz de succes/esec
     */
    public boolean deleteClient(int id){
        return clientDAO.delete(id);

    }

    /**
     * Metoda de cautare a unui client dupa nume sau ID
     * @param field - numele clientului
     * @return -lista de clienti
     */
    public List<Client> searchClient(String field){
        List<Client> foundClientsList=new ArrayList<>();
        int id=-1;
        try{
            id=Integer.parseInt(field);
            Client client = clientDAO.findById(id);
            if(client!=null){
                foundClientsList.add(client);
                return foundClientsList;
            } else {
                return null;
            }
        }catch(NumberFormatException e){
            return clientDAO.findClientByName(field);
        }
    }

    /**
     * Metoda care returneaza o lista a tuturor clientilor din baza de date
     * @return o lista de clienti
     * @throws RuntimeException
     */
    public List<Client> viewAll() throws RuntimeException{
        List<Client> clientsList= clientDAO.findAll();
        if(clientsList==null){
            throw new RuntimeException();
        }
        return clientsList;
    }


}
