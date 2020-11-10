package com.my.shareonfoot.codi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.my.shareonfoot.clothes.vo.ClothesVO;
import com.my.shareonfoot.codi.vo.CodiVO;

@Repository("codiDAO")
public class CodiDAOImpl implements CodiDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<CodiVO> selectAllCodi() throws DataAccessException {
		//鍮� �븘�꽣瑜� 蹂대깂�쑝濡쒖뜥 紐⑤뱺 肄붾뵒 議고쉶
		List<CodiVO> codilist = sqlSession.selectList("mapper.codi.searchCodi", new CodiVO());
		return codilist;
	}

	@Override
	public CodiVO selectThisCodi(String codiNo) throws DataAccessException {
		
		CodiVO codiVO = new CodiVO();
		codiVO.setCodiNo(Integer.parseInt(codiNo));
		
		CodiVO codi = sqlSession.selectOne("mapper.codi.searchCodi",codiVO);
		return codi;
	}

	@Override
	public List<CodiVO> selectCodi(CodiVO codiFilter) throws DataAccessException {
		List<CodiVO> codiList = sqlSession.selectList("mapper.codi.searchCodi", codiFilter);
		return codiList;
	}

	@Override
	public String addCodi(Map<String, Object> codiInfo) throws DataAccessException {

		System.out.println("insert 荑쇰━ �떎�뻾 �쟾");
		int result = sqlSession.insert("mapper.codi.insertCodi", codiInfo);
		System.out.println("insert 荑쇰━ �떎�뻾");
		
		if (result == 1) {
			System.out.println("荑쇰━ �꽦怨�");
			return "ok"; //insert �꽦怨�
		}
		else {
			System.out.println("荑쇰━ �떎�뙣");
			return "fail"; //insert �떎�뙣
		}			
	}
	
	@Override
	public String addCodiData(CodiVO codiInfo) throws DataAccessException {

		System.out.println("insert 荑쇰━ �떎�뻾 �쟾");
		int result = sqlSession.insert("mapper.codi.insertCodiData", codiInfo);
		System.out.println("insert 荑쇰━ �떎�뻾");
		
		if (result == 1) {
			System.out.println("insert �꽦怨�");
			return "ok"; //insert �꽦怨�
		}
		else {
			System.out.println("insert �떎�뙣");
			return "fail"; //insert �떎�뙣
		}		
	}	
	
	

	@Override
	public String updateCodi(CodiVO codiInfo) throws DataAccessException {
		int result = sqlSession.update("mapper.codi.updateCodi",codiInfo);
		
		if (result==1)
			return "ok"; //update �꽦怨�
		else
			return "fail"; //update �떎�뙣
	}

	@Override
	public String deleteCodi(String codiNo) throws DataAccessException {
		int result = sqlSession.delete("mapper.codi.deleteCodi",codiNo);
		
		if (result==1)
			return "ok";
		else
			return "fail";
	}

}
