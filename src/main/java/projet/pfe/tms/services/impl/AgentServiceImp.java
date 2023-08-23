package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.AgentDTO;
import projet.pfe.tms.models.*;
import projet.pfe.tms.repositories.AgentRepo;
import projet.pfe.tms.repositories.FolderRepo;
import projet.pfe.tms.services.AgentService;
import projet.pfe.tms.services.CountryService;
import projet.pfe.tms.services.CurrencyService;
import projet.pfe.tms.services.StaffService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentServiceImp implements AgentService {

    private final AgentRepo agentRepo;
    private final CountryService countryService;
    private final CurrencyService currencyService;
    private final StaffService staffService;
    private final FolderRepo folderRepo;

    @Autowired
    public AgentServiceImp(AgentRepo agentRepo,
                           CountryService countryService,
                           CurrencyService currencyService,
                           StaffService staffService, FolderRepo folderRepo){
        this.agentRepo = agentRepo;
        this.countryService = countryService;
        this.currencyService = currencyService;
        this.staffService = staffService;
        this.folderRepo = folderRepo;
    }

    @Transactional
    @Override
    public Agent addNewAgent(AgentDTO agentDto) {
        Agent agent = new Agent();
        agent.setCompany(agentDto.getCompany());
        agent.setPhoneNumber(agentDto.getPhoneNumber());
        agent.setEmail(agentDto.getEmail());
        agent.setWebsite(agentDto.getWebsite());
        agent.setCNSS(agentDto.getCnss());
        agent.setAddress(agentDto.getAddress());
        agent.setCity(agentDto.getCity());
        agent.setZip(agentDto.getZip());

        // set agent's country
        if(agentDto.getCountryId() != null) {
            Country agentCountry = this.countryService.getCountryById(agentDto.getCountryId());
            agent.setCountry(agentCountry);
        }
        agent.setPatente(agentDto.getPatente());

        // set agent's currency
        if(agentDto.getDefaultCurrencyId() != null) {
            Currency agentCurrency = this.currencyService.getCurrencyById(agentDto.getDefaultCurrencyId());
            agent.setCurrency(agentCurrency);
        }

        agent.setBillingStreet(agentDto.getBillingStreet());
        agent.setBillingState(agentDto.getBillingState());
        agent.setBillingCity(agentDto.getBillingCity());
        agent.setBillingZip(agentDto.getBillingZip());

        // set agent billing country
        if(agentDto.getBillingCountryId() != null) {
            Country billingCountry = this.countryService.getCountryById(agentDto.getBillingCountryId());
            agent.setBillingCountry(billingCountry);
        }

        agent.setShippingStreet(agentDto.getShippingStreet());
        agent.setShippingState(agentDto.getShippingState());
        agent.setShippingCity(agentDto.getShippingCity());
        agent.setShippingZip(agentDto.getShippingZip());

        // set agent shipping country
        if(agentDto.getShippingCountryId() != null) {
            Country shippingCountry = this.countryService.getCountryById(agentDto.getShippingCountryId());
            agent.setShippingCountry(shippingCountry);
        }

        return this.agentRepo.save(agent);
    }

    @Transactional
    @Override
    public Agent updateAgent(Long id, AgentDTO agentDto) {
        Agent agent = this.agentRepo.findById(id).orElse(null);
        if(agent != null){
            agent = this.updateData(agent, agentDto);

            // set agent's currency
            Currency agentCurrency = this.currencyService.getCurrencyById(agentDto.getDefaultCurrencyId());
            agent.setCurrency(agentCurrency);

            // set agent's country
            Country agentCountry = this.countryService.getCountryById(agentDto.getCountryId());
            agent.setCountry(agentCountry);

            // set agent billing country
            Country billingCountry = this.countryService.getCountryById(agentDto.getBillingCountryId());
            agent.setBillingCountry(billingCountry);

            // set agent shipping country
            Country shippingCountry = this.countryService.getCountryById(agentDto.getShippingCountryId());
            agent.setShippingCountry(shippingCountry);

            // set the collaborator in charge of that customer
            Staff collaborator = this.staffService.loadUserById(agentDto.getStaffId());
            agent.setStaff(collaborator);

            return this.agentRepo.save(agent);
        }
        return null;
    }

    @Override
    public Agent updateData(Agent agent, AgentDTO agentDto) {
        agent.setCompany(agentDto.getCompany());
        agent.setPhoneNumber(agentDto.getPhoneNumber());
        agent.setEmail(agentDto.getEmail());
        agent.setWebsite(agentDto.getWebsite());
        agent.setCNSS(agentDto.getCnss());
        agent.setAddress(agentDto.getAddress());
        agent.setCity(agentDto.getCity());//
        agent.setZip(agentDto.getZip());
        agent.setPatente(agentDto.getPatente());
        agent.setDateAffectationOfCommercial(agentDto.getDateAffectation());
        agent.setBillingStreet(agentDto.getBillingStreet());//
        agent.setBillingState(agentDto.getBillingState());
        agent.setBillingCity(agentDto.getBillingCity());
        agent.setBillingZip(agentDto.getBillingZip());
        agent.setShippingStreet(agentDto.getShippingStreet());
        agent.setShippingState(agentDto.getShippingState());
        agent.setShippingCity(agentDto.getShippingCity());
        agent.setShippingZip(agentDto.getShippingZip());

        return agent;
    }

    @Override
    public void deleteAgent(Long id) {

        agentRepo.deleteById(id);
        Agent agent = agentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("recipient not found"));
        List<Folder> folderList = folderRepo.findFolderByAgent(agent);
        for (Folder folder : folderList) {
            folder.setAgent(null);
            folderRepo.save(folder);
        }

        agentRepo.delete(agent);
    }

    @Override
    public List<Agent> listAgents() {
        return this.agentRepo.findAll();
    }

    @Override
    public List<AgentDTO> listAgentsDTO() {
        List<Agent> agents = this.agentRepo.findAll();
        List<AgentDTO> agentsDTO = agents.stream()
                .map(this::convertToAgentDTO)
                .collect(Collectors.toList());
        return agentsDTO; // Return the list of AgentDTOs
    }

    public AgentDTO convertToAgentDTO(Agent agent) {
         if(agent == null){
            return null;
        }
        AgentDTO agentDto = new AgentDTO();
        agentDto.setAgentId(agent.getAgentId());
        agentDto.setName(agent.getName());
        agentDto.setCompany(agent.getCompany());
        agentDto.setActive(agent.isActive());
        agentDto.setPhoneNumber(agent.getPhoneNumber());
        agentDto.setEmail(agent.getEmail());
        agentDto.setWebsite(agent.getWebsite());
        agentDto.setCnss(agent.getCNSS());
        agentDto.setAddress(agent.getAddress());
        agentDto.setCity(agent.getCity());
        agentDto.setZip(agent.getZip());
        if(agent.getCountry() != null){
            agentDto.setCountryId(agent.getCountry().getCountryId());
            agentDto.setCountryLongName(agent.getCountry().getLongName());
        }
        agentDto.setPatente(agent.getPatente());
        if(agent.getCurrency() != null)
            agentDto.setDefaultCurrencyId(agent.getCurrency().getCurrencyId());
        agentDto.setBillingStreet(agent.getBillingStreet());
        agentDto.setBillingState(agent.getBillingState());
        agentDto.setBillingCity(agent.getBillingCity());
        agentDto.setBillingZip(agent.getBillingZip());
        if(agent.getBillingCountry() != null)
            agentDto.setBillingCountryId(agent.getBillingCountry().getCountryId());
        agentDto.setShippingStreet(agent.getShippingStreet());
        agentDto.setShippingState(agent.getShippingState());
        agentDto.setShippingCity(agent.getShippingCity());
        agentDto.setShippingZip(agent.getShippingZip());
        if(agent.getShippingCountry() != null)
            agentDto.setShippingCountryId(agent.getShippingCountry().getCountryId());
        if(agent.getStaff() != null) {
            agentDto.setStaffId(agent.getStaff().getStaffId());
            agentDto.setStaffFullName(agent.getStaff().getFirstName() + " " + agent.getStaff().getLastName());
            agentDto.setDateAffectation(agent.getDateAffectationOfCommercial());
        }

        return agentDto;
    }

    @Override
    public Agent loadAgentById(Long id) {
        return this.agentRepo
                .findById(id)
                .orElse(null);
    }

    @Override
    public AgentDTO loadAgentByAgentId(Long id) {
        Agent agent = this.agentRepo.findById(id).orElse(null);
        if(agent == null){
            return null;
        }
        AgentDTO agentDto = new AgentDTO();
        agentDto.setCompany(agent.getCompany());
        agentDto.setPhoneNumber(agent.getPhoneNumber());
        agentDto.setEmail(agent.getEmail());
        agentDto.setWebsite(agent.getWebsite());
        agentDto.setCnss(agent.getCNSS());
        agentDto.setAddress(agent.getAddress());
        agentDto.setCity(agent.getCity());
        agentDto.setZip(agent.getZip());
        if(agent.getCountry() != null)
            agentDto.setCountryId(agent.getCountry().getCountryId());
        agentDto.setPatente(agent.getPatente());
        if(agent.getCurrency() != null)
            agentDto.setDefaultCurrencyId(agent.getCurrency().getCurrencyId());

        agentDto.setBillingStreet(agent.getBillingStreet());
        agentDto.setBillingState(agent.getBillingState());
        agentDto.setBillingCity(agent.getBillingCity());
        agentDto.setBillingZip(agent.getBillingZip());
        if(agent.getBillingCountry() != null)
            agentDto.setBillingCountryId(agent.getBillingCountry().getCountryId());

        agentDto.setShippingStreet(agent.getShippingStreet());
        agentDto.setShippingState(agent.getShippingState());
        agentDto.setShippingCity(agent.getShippingCity());
        agentDto.setShippingZip(agent.getShippingZip());
        if(agent.getShippingCountry() != null)
            agentDto.setShippingCountryId(agent.getShippingCountry().getCountryId());

        if(agent.getStaff() != null) {
            agentDto.setStaffId(agent.getStaff().getStaffId());
            agentDto.setStaffFullName(agent.getStaff().getFirstName() + " " + agent.getStaff().getLastName());
            agentDto.setDateAffectation(agent.getDateAffectationOfCommercial());
        }

        return agentDto;
    }


    @Override
    public Agent loadAgentByEmail(String email) {
        return this.agentRepo
                .findByEmail(email)
                .orElse(null);
    }

    @Override
    public Agent loadAgentByCompany(String company) {
        return this.agentRepo
                .findByCompany(company)
                .orElse(null);
    }



    @Override
    public Agent affectCommercialToAgent(Long idAgent, Long idStaff) {
        Agent agent = this.loadAgentById(idAgent);
        Staff staff = this.staffService.loadUserById(idStaff);
        if(agent != null){
            agent.setStaff(staff);
            agent.setDateAffectationOfCommercial(LocalDateTime.now());
            return this.agentRepo.save(agent);
        }
        return null;
    }

    @Override
    public Agent deleteCommercialOfAgent(Long id) {
        Agent agent = this.loadAgentById(id);
        agent.setStaff(null);
        agent.setDateAffectationOfCommercial(null);
        return this.agentRepo.save(agent);
    }

    @Override
    public int countTotalAgents() {
        return (int) agentRepo.count();
    }

    @Override
    public int countActiveAgents() {
        return agentRepo.countByActive(true);
    }

    @Override
    public int countInactiveAgents() {
        return agentRepo.countByActive(false);
    }

    @Override
    public Agent updateAgentActiveStatus(Long agentId, boolean active) {
        Agent agent = agentRepo.findByAgentId(agentId);
        agent.setActive(active);
        return agentRepo.save(agent);
    }
}
