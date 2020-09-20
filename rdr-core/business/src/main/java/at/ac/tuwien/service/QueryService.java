package at.ac.tuwien.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import at.ac.tuwien.utils.QueryUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.api.dto.ExecuteQueryDto;
import at.ac.tuwien.api.dto.TableDto;
import at.ac.tuwien.persistence.impl.DataStoreDaoImpl;
import at.ac.tuwien.querystore.service.QueryStoreService;

import javax.ws.rs.core.Response;

@Service
public class QueryService {

    private DataStoreDaoImpl impl;

    private QueryStoreService queryStoreService;

    @Autowired
    public QueryService(DataStoreDaoImpl impl, QueryStoreService queryStoreService) {
        this.impl = impl;
        this.queryStoreService = queryStoreService;
    }

    public ExecuteQueryDto getResultOfQuery(ExecuteQueryDto dto) {
        ResultSet rs = impl.executeQuery(dto.getQuery());
        try {
            dto.setResult(QueryUtils.resultSetToList(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        queryStoreService.storeQuery(dto, rs);

        return dto;
    }

    public TableDto resolvePID(int pid) {
        return queryStoreService.resolvePID(pid);
    }

}
