/**
 * Copyright 2009-2015 the original author or authors. Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless
 * required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */

package issue483;

import static org.junit.Assert.*;

import java.io.Reader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

public class Issue483Test {

  private static SqlSessionFactory sqlSessionFactory;

  @BeforeClass
  public static void setUp() throws Exception {
    // create an SqlSessionFactory
    Reader reader = Resources.getResourceAsReader("issue483/mybatis-config.xml");
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    reader.close();

    // populate in-memory database
    SqlSession session = sqlSessionFactory.openSession();
    Connection conn = session.getConnection();
    reader = Resources.getResourceAsReader("issue483/CreateDB.sql");
    ScriptRunner runner = new ScriptRunner(conn);
    runner.setLogWriter(null);
    runner.runScript(reader);
    reader.close();
    session.close();
  }

  @Test
  public void testSaveOrderDetail_1() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      OrderDao mapper = sqlSession.getMapper(OrderDao.class);
      List<OrderDetail> details = new ArrayList<OrderDetail>();
      OrderDetail detail = new OrderDetail();
      detail.setId(1);
      detail.setAmount(100);
      detail.setCount(2);
      details.add(detail);
      Order order = new Order();
      order.setDetails(details);
      int result = mapper.saveOrderDetail_1(order);
      assertEquals(1, result);
    } finally {
      sqlSession.close();
    }
  }

  @Test
  public void testSaveOrderDetail_2() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      OrderDao mapper = sqlSession.getMapper(OrderDao.class);
      List<OrderDetail> details = new ArrayList<OrderDetail>();
      OrderDetail detail = new OrderDetail();
      detail.setId(1);
      detail.setAmount(100);
      detail.setCount(2);
      details.add(detail);
      int result = mapper.saveOrderDetail_2(details);
      assertEquals(1, result);
    } finally {
      sqlSession.close();
    }
  }

  @Test
  public void testSaveOrderDetail_3() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      OrderDao mapper = sqlSession.getMapper(OrderDao.class);
      List<OrderDetail> details = new ArrayList<OrderDetail>();
      OrderDetail detail = new OrderDetail();
      detail.setId(1);
      detail.setAmount(100);
      detail.setCount(2);
      details.add(detail);
      int result = mapper.saveOrderDetail_3(details);
      assertEquals(1, result);
    } finally {
      sqlSession.close();
    }
  }
}
