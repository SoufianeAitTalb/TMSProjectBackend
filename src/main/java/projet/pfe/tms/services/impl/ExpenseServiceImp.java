package projet.pfe.tms.services.impl;

import org.springframework.stereotype.Service;
import projet.pfe.tms.models.*;
import projet.pfe.tms.dto.ExpenseDTO;
import projet.pfe.tms.repositories.*;
import projet.pfe.tms.services.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImp implements ExpenseService {

    private final  ExpenseRepo expenseRepo;
    private final  ClientService clientService;
    private final  SupplierService supplierService;
    private final  FolderService folderService;
    private final ExpenseCategoriesService expenseCategoriesService;
    private final SupplierRepo supplierRepo;
    private final ClientRepo clientRepo;
    private final FolderRepo folderRepo;
    private final ExpenseCategoriesRepo expenseCategoriesRepo;

    public ExpenseServiceImp(ExpenseRepo expenseRepo, ClientService clientService, SupplierService supplierService, FolderService folderService, ExpenseCategoriesService expenseCategoriesService, SupplierRepo supplierRepo, ClientRepo clientRepo, FolderRepo folderRepo, ExpenseCategoriesRepo expenseCategoriesRepo) {
        this.expenseRepo = expenseRepo;
        this.clientService = clientService;
        this.supplierService = supplierService;
        this.folderService = folderService;
        this.expenseCategoriesService = expenseCategoriesService;

        this.supplierRepo = supplierRepo;
        this.clientRepo = clientRepo;
        this.folderRepo = folderRepo;
        this.expenseCategoriesRepo = expenseCategoriesRepo;
    }


    public List<ExpenseDTO> getAllExpenses() {

        List<Expense> expenses = this.expenseRepo.findAll();
        List<ExpenseDTO> expenseDTOS = new ArrayList<>();
        for (Expense expense : expenses) {
            ExpenseDTO expenseDTO= new ExpenseDTO();
         expenseDTO.setExpenseId(expense.getExpenseId());
         expenseDTO.setAmount(expense.getAmount());
         expenseDTO.setEtat(expense.getEtat());
         expenseDTO.setDate(expense.getDate());
         expenseDTO.setComment(expense.getComment());
         expenseDTO.setNumFacture(expense.getNumFacture());
         expenseDTO.setType(expense.getType());
         expenseDTO.setCategoryExpenseId(expense.getCategoryExpense() !=null ? expense.getCategoryExpense().getCategoryExpenseId() :null);
         expenseDTO.setPayeur(expense.getPayeur());
         expenseDTO.setCategoryFacturation(expense.getCategoryFacturation());
         expenseDTO.setExpNumber(expense.getExpNumber());
       //  expenseDTO.setClientId(expense.getClient() != null ? expense.getClient().getClientId() : null);
         expenseDTO.setFolderId(expense.getFolder() !=null ? expense.getFolder().getFolderId() : null);
         expenseDTO.setSupplierId(expense.getSupplier() != null ? expense.getSupplier().getUserId() : null);
         expenseDTOS.add(expenseDTO);
        }
        return expenseDTOS;
    }

    public Expense getExpenseById(Long expenseId) {
        Expense expense=expenseRepo.findById(expenseId).orElse(null);
        if(expense == null){
        return null;
        }
        return expense;
    }

    public Expense createExpense(ExpenseDTO expenseDTO) {
        Expense expense = new Expense();
        expense.setExpNumber(expenseDTO.getExpNumber());
        expense.setAmount(expenseDTO.getAmount());
        expense.setPayeur(expenseDTO.getPayeur());
        expense.setCategoryFacturation(expenseDTO.getCategoryFacturation());
        expense.setComment(expenseDTO.getComment());
        /*
        Client client = this.clientService.loadClientById(expenseDTO.getClientId());

            expense.setClient(client);

       */
        Folder folder = this.folderService.getFolderById(expenseDTO.getFolderId());
        expense.setFolder(folder);
        //
        Supplier supplier = this.supplierService.loadById(expenseDTO.getSupplierId());
        expense.setSupplier(supplier);
        //
        ExpenseCategories expenseCategories= this.expenseCategoriesService.getExpenseCategoryById(expenseDTO.getCategoryExpenseId());
        expense.setCategoryExpense(expenseCategories);
        expense.setNumFacture(expenseDTO.getNumFacture());
        expense.setType(expenseDTO.getType());
        expense.setEtat(expenseDTO.getEtat());
        expense.setDate(expenseDTO.getDate());
        return expenseRepo.save(expense);
    }

    public Expense updateExpense(Long expenseId, ExpenseDTO expenseDTO) {
        Expense expense = expenseRepo.findById(expenseId).orElse(null);
        expense.setExpNumber(expenseDTO.getExpNumber());
        expense.setAmount(expenseDTO.getAmount());
        expense.setPayeur(expenseDTO.getPayeur());
        expense.setCategoryFacturation(expenseDTO.getCategoryFacturation());
        expense.setComment(expenseDTO.getComment());
        /*
        Client client = this.clientService.loadClientById(expenseDTO.getClientId());

        expense.setClient(client);
        */
        Folder folder = this.folderService.getFolderById(expenseDTO.getFolderId());
        expense.setFolder(folder);
        //
        Supplier supplier = this.supplierService.loadById(expenseDTO.getSupplierId());
        expense.setSupplier(supplier);
        //
        ExpenseCategories expenseCategories= this.expenseCategoriesService.getExpenseCategoryById(expenseDTO.getCategoryExpenseId());
        expense.setCategoryExpense(expenseCategories);
        //
        expense.setNumFacture(expenseDTO.getNumFacture());
        expense.setType(expenseDTO.getType());
        expense.setEtat(expenseDTO.getEtat());
        expense.setDate(expenseDTO.getDate());
        return expenseRepo.save(expense);
    }

    @Transactional
    public void deleteExpense(Long id) {
        Optional<Expense> optionalExpense = expenseRepo.findById(id);
        if (optionalExpense.isPresent()) {
            Expense expense = optionalExpense.get();

            // Remove expense reference from supplier
            Supplier supplier = expense.getSupplier();
            if (supplier != null) {
                supplier.getSupplierExpenses().remove(expense);
                supplierRepo.save(supplier);
            }

            /* Remove expense reference from client
            Client client = expense.getClient();
            if (client != null) {
                client.getClientExpenses().remove(expense);
                clientRepo.save(client);
            }
           */
            // Remove expense reference from EXPENSE CATEGORIES
            ExpenseCategories expenseCategories = expense.getCategoryExpense();
            if (expenseCategories != null) {
                expenseCategories.getExpenses().remove(expense);
                expenseCategoriesRepo.save(expenseCategories);
            }

            expenseRepo.delete(expense);
        } else {
            throw new RuntimeException("Folder not found with id " + id);
        }
    }
}