package com.braavocreations.office_management.repositories;

import com.braavocreations.office_management.domain.Contract;
import com.braavocreations.office_management.exceptions.BadRequestException;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ContractRepositoryImpl implements ContractRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_ALL = "SELECT * FROM CONTRACT_TABLE";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM CONTRACT_TABLE WHERE cont_id = ? ";
    private static final String SQL_CREATE = "INSERT INTO contract_table (cont_id, cont_name, cont_date_handeover) VALUES( ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE contract_table SET cont_name = ?, cont_date_handeover = ? " +
            "WHERE cont_id = ?";
    private static final String SQL_DELETE_CONTRACT = "DELETE FROM contract_table WHERE cont_id = ?";




    @Override
    public List<Contract> findAll() throws ResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{},contractRowMapper);
    }

    @Override
    public Contract findById(Integer cont_id) throws ResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{cont_id}, contractRowMapper);
        }catch (Exception e){
            throw new ResourceNotFoundException("Contract not found");
        }
    }

    @Override
    public Contract create(String cont_name, long cont_date_handoever) throws BadRequestException {
        List<Contract> contArray = findAll();
        Integer cont_id;
        if(contArray.size() != 0){
            Contract contObj = contArray.get(contArray.size()-1);
             cont_id = contObj.getCont_id()+1;
        }else {
            cont_id = 1;
        }
        Integer intDate = Math.toIntExact(cont_date_handoever);
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->{
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,cont_id);
                ps.setString(2,cont_name);
                ps.setInt(3,intDate);
                return ps;
            },keyHolder);
//            Integer createdContID = (Integer) keyHolder.getKeys().get("CONT_ID");
//            System.out.println("createdContID");
//            System.out.println(createdContID);
//            System.out.println("createdContID");
            return findById(cont_id);
        }catch (Exception e){
            System.out.println(e);
            throw new BadRequestException("Invalid Request");
        }
    }

    @Override
    public Contract update(Integer cont_id, String cont_name, long cont_date_handoever) throws BadRequestException {
        try{
            jdbcTemplate.update(SQL_UPDATE, new Object[]{cont_name,cont_date_handoever,cont_id});
            return findById(cont_id);
        }catch (Exception e){
            throw new BadRequestException("Invalid Request");
        }
    }

    @Override
    public void removeById(Integer cont_id) throws ResourceNotFoundException {
        try {
            jdbcTemplate.update(SQL_DELETE_CONTRACT, new Object[]{cont_id});
        }catch (Exception e){
            throw new BadRequestException("Invalid Request");
        }

    }

    private RowMapper<Contract> contractRowMapper = ((rs, rowNum) -> {
        return new Contract(rs.getInt("cont_id"),
                rs.getString("cont_name"),
                rs.getLong("cont_date_handeover"));
    });
}
