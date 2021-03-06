package com.studyhub.crowd.mapper;

import com.studyhub.crowd.entity.Admin;
import com.studyhub.crowd.entity.AdminExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> selectAdminListByKeyword(String keyword);

    void deleteAdminRoleRelationship(Integer adminId);

    void insertAdminRoleRelationship(@Param("adminId") Integer adminId, @Param("roleList") List<Integer> roleList);
}