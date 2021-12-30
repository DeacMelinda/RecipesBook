package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

	private final String url = "jdbc:postgresql://localhost/recipesbook";
	private final String user = "postgres";
	private final String password = "1234567890";
	
	public Connection connect() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public int setRows() {
		String query = "SELECT COUNT(*) FROM recipes";
		int nrRows = 0;
		try (Connection conn = connect();
	    		Statement statement = conn.createStatement();) {
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			nrRows = rs.getInt(1);
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return nrRows;
	}
	
	public int getIngrRows() {
		String query = "SELECT COUNT(*) FROM ingredients";
		int nrRows = 0;
		try (Connection conn = connect();
	    		Statement statement = conn.createStatement();) {
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			nrRows = rs.getInt(1);
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return nrRows;
	}
	
	public int getStepsRows() {
		String query = "SELECT COUNT(*) FROM steps";
		int nrRows = 0;
		try (Connection conn = connect();
	    		Statement statement = conn.createStatement();) {
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			nrRows = rs.getInt(1);
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return nrRows;
	}
	
	int nrIngrRows = getIngrRows();
	int nrStepsRows = getStepsRows();
	
	public void insertIngredients(List<Ingredients> list) {
		String query = "INSERT INTO ingredients(ingredient_id, ingredient, quantity, measuring_unit, recipe_id) " + "VALUES (?,?,?,?,?)";
	    try (Connection conn = connect();
	    		PreparedStatement statement = conn.prepareStatement(query);) {
	    	int count = 0;
	    	for (Ingredients ingr : list) {
	    		statement.setInt(1, nrIngrRows);
	    		nrIngrRows++;
	        	statement.setString(2, ingr.getName());
	            statement.setDouble(3, ingr.getQuantity());
	            statement.setString(4, ingr.getMeasuring_unit());
	            statement.setInt(5, ingr.getRecipe_id());
                statement.addBatch();
	            count++;
	                // execute every 100 rows or less
	            if (count % 100 == 0 || count == list.size()) {
	            	statement.executeBatch();
	            }
	        }
	   } catch (SQLException ex) {
		   System.out.println(ex.getMessage());
	   }
	}
	
	public void insertSteps(List<Steps> list) {
		String query = "INSERT INTO steps(step_id, step_nr, step_description, recipe_id) " + "VALUES (?,?,?,?)";
	    try (Connection conn = connect();
	    		PreparedStatement statement = conn.prepareStatement(query);) {
	    	int count = 0;
	    	for (Steps step : list) {
	    		statement.setInt(1, nrStepsRows);
	    		nrStepsRows++;
	        	statement.setInt(2, step.getNr());
	            statement.setString(3, step.getDescription());
	            statement.setInt(4, step.getRecipe_id());
                statement.addBatch();
	            count++;
	                // execute every 100 rows or less
	            if (count % 100 == 0 || count == list.size()) {
	            	statement.executeBatch();
	            }
	        }
	   } catch (SQLException ex) {
		   System.out.println(ex.getMessage());
	   }
	}
	
	public long insertRecipeBreakfast(Recipe r) {
		
		long id = -1;
		String query = "INSERT INTO recipes (recipe_id, recipe_name, calories_per_serving, servings, serving_size_in_grams, difficulty, book_id) " + "VALUES(?,?,?,?,?,?,?)";
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			
			pstmt.setInt(1, r.getRecipe_id());
			pstmt.setString(2, r.getName());
			pstmt.setDouble(3, r.getCalories_per_serving());
			pstmt.setInt(4, r.getServings());
			pstmt.setDouble(5, r.getServing_size_in_grams());
			pstmt.setString(6, r.getDifficulty()+" out of 100");
			pstmt.setInt(7, 4);

			
			int affectedRows = pstmt.executeUpdate();
			if(affectedRows > 0) {
				try(ResultSet rs = pstmt.getGeneratedKeys()) {
					if(rs.next()) {
						id = rs.getLong(1);
					}
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return id;
	}
	
	public long insertRecipeChristmas(Recipe r) {
			
			long id = -1;
			String query = "INSERT INTO recipes (recipe_id, recipe_name, calories_per_serving, servings, serving_size_in_grams, difficulty, book_id) " + "VALUES(?,?,?,?,?,?,?)";
			try (Connection conn = connect();
					PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				
				pstmt.setInt(1, r.getRecipe_id());
				pstmt.setString(2, r.getName());
				pstmt.setDouble(3, r.getCalories_per_serving());
				pstmt.setInt(4, r.getServings());
				pstmt.setDouble(5, r.getServing_size_in_grams());
				pstmt.setString(6, r.getDifficulty()+" out of 100");
				pstmt.setInt(7, 5);
	
				
				int affectedRows = pstmt.executeUpdate();
				if(affectedRows > 0) {
					try(ResultSet rs = pstmt.getGeneratedKeys()) {
						if(rs.next()) {
							id = rs.getLong(1);
						}
					} catch (SQLException ex) {
						System.out.println(ex.getMessage());
					}
				}
				
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			return id;
		}
	
	public long insertRecipeCocktails(Recipe r) {
		
		long id = -1;
		String query = "INSERT INTO recipes (recipe_id, recipe_name, calories_per_serving, servings, serving_size_in_grams, difficulty, book_id) " + "VALUES(?,?,?,?,?,?,?)";
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			
			pstmt.setInt(1, r.getRecipe_id());
			pstmt.setString(2, r.getName());
			pstmt.setDouble(3, r.getCalories_per_serving());
			pstmt.setInt(4, r.getServings());
			pstmt.setDouble(5, r.getServing_size_in_grams());
			pstmt.setString(6, r.getDifficulty()+" out of 100");
			pstmt.setInt(7, 6);
	
			
			int affectedRows = pstmt.executeUpdate();
			if(affectedRows > 0) {
				try(ResultSet rs = pstmt.getGeneratedKeys()) {
					if(rs.next()) {
						id = rs.getLong(1);
					}
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return id;
	}
	
	public long insertRecipeDinners(Recipe r) {
		
		long id = -1;
		String query = "INSERT INTO recipes (recipe_id, recipe_name, calories_per_serving, servings, serving_size_in_grams, difficulty, book_id) " + "VALUES(?,?,?,?,?,?,?)";
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			
			pstmt.setInt(1, r.getRecipe_id());
			pstmt.setString(2, r.getName());
			pstmt.setDouble(3, r.getCalories_per_serving());
			pstmt.setInt(4, r.getServings());
			pstmt.setDouble(5, r.getServing_size_in_grams());
			pstmt.setString(6, r.getDifficulty()+" out of 100");
			pstmt.setInt(7, 3);
	
			
			int affectedRows = pstmt.executeUpdate();
			if(affectedRows > 0) {
				try(ResultSet rs = pstmt.getGeneratedKeys()) {
					if(rs.next()) {
						id = rs.getLong(1);
					}
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return id;
	}
	
	public long insertRecipeSoups(Recipe r) {
		
		long id = -1;
		String query = "INSERT INTO recipes (recipe_id, recipe_name, calories_per_serving, servings, serving_size_in_grams, difficulty, book_id) " + "VALUES(?,?,?,?,?,?,?)";
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			
			pstmt.setInt(1, r.getRecipe_id());
			pstmt.setString(2, r.getName());
			pstmt.setDouble(3, r.getCalories_per_serving());
			pstmt.setInt(4, r.getServings());
			pstmt.setDouble(5, r.getServing_size_in_grams());
			pstmt.setString(6, r.getDifficulty()+" out of 100");
			pstmt.setInt(7, 2);
	
			
			int affectedRows = pstmt.executeUpdate();
			if(affectedRows > 0) {
				try(ResultSet rs = pstmt.getGeneratedKeys()) {
					if(rs.next()) {
						id = rs.getLong(1);
					}
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return id;
	}
	
	public long insertRecipeSweets(Recipe r) {
		
		long id = -1;
		String query = "INSERT INTO recipes (recipe_id, recipe_name, calories_per_serving, servings, serving_size_in_grams, difficulty, book_id) " + "VALUES(?,?,?,?,?,?,?)";
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			
			pstmt.setInt(1, r.getRecipe_id());
			pstmt.setString(2, r.getName());
			pstmt.setDouble(3, r.getCalories_per_serving());
			pstmt.setInt(4, r.getServings());
			pstmt.setDouble(5, r.getServing_size_in_grams());
			pstmt.setString(6, r.getDifficulty()+" out of 100");
			pstmt.setInt(7, 1);

			
			int affectedRows = pstmt.executeUpdate();
			if(affectedRows > 0) {
				try(ResultSet rs = pstmt.getGeneratedKeys()) {
					if(rs.next()) {
						id = rs.getLong(1);
					}
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return id;
	}
	
	public ArrayList<Recipe> getBreakfastRecipes() {
		ArrayList<Recipe> recipes = new ArrayList<>();
		
		String query = "SELECT * FROM recipes WHERE book_id = 4";
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			
			while(rs.next()) {
				Recipe r = new Recipe();
				r.setName(rs.getString("recipe_name"));
				r.setCalories_per_serving(rs.getDouble("calories_per_serving"));
				String diff[] = rs.getString("difficulty").split(" ", 2);
				r.setDifficulty(Double.parseDouble(diff[0]));
				r.setServing_size_in_grams(rs.getDouble("serving_size_in_grams"));
				r.setServings(rs.getInt("servings"));
				r.setRecipe_id(rs.getInt("recipe_id"));
				
				String q1 = "SELECT * FROM ingredients WHERE recipe_id = " + r.getRecipe_id();
				
				Statement stmt1 = conn.createStatement();
				ResultSet rs1 = stmt1.executeQuery(q1);
				while(rs1.next()) {
					Ingredients i = new Ingredients();
					i.setMeasuring_unit(rs1.getString("measuring_unit"));
					i.setName(rs1.getString("ingredient"));
					i.setQuantity(rs1.getDouble("quantity"));
					i.setRecipe_id(r.getRecipe_id());
					r.addIngredients(i);
				}
				
				String q2 = "SELECT * FROM steps WHERE recipe_id = " + r.getRecipe_id() + " ORDER BY step_nr";
				
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(q2);
				while(rs2.next()) {
					Steps s = new Steps();
					s.setDescription(rs2.getString("step_description"));
					s.setNr(rs2.getInt("step_nr"));
					s.setRecipe_id(r.getRecipe_id());
					r.addSteps(s);
				}
				recipes.add(r);
			}	
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return recipes;
	}
	
	public ArrayList<Recipe> getSweetRecipes() {
		ArrayList<Recipe> recipes = new ArrayList<>();
		
		String query = "SELECT * FROM recipes WHERE book_id = 1";
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			
			while(rs.next()) {
				Recipe r = new Recipe();
				r.setName(rs.getString("recipe_name"));
				r.setCalories_per_serving(rs.getDouble("calories_per_serving"));
				String diff[] = rs.getString("difficulty").split(" ", 2);
				r.setDifficulty(Double.parseDouble(diff[0]));
				r.setServing_size_in_grams(rs.getDouble("serving_size_in_grams"));
				r.setServings(rs.getInt("servings"));
				r.setRecipe_id(rs.getInt("recipe_id"));
				
				String q1 = "SELECT * FROM ingredients WHERE recipe_id = " + r.getRecipe_id();
				
				Statement stmt1 = conn.createStatement();
				ResultSet rs1 = stmt1.executeQuery(q1);
				while(rs1.next()) {
					Ingredients i = new Ingredients();
					i.setMeasuring_unit(rs1.getString("measuring_unit"));
					i.setName(rs1.getString("ingredient"));
					i.setQuantity(rs1.getDouble("quantity"));
					i.setRecipe_id(r.getRecipe_id());
					r.addIngredients(i);
				}
				
				String q2 = "SELECT * FROM steps WHERE recipe_id = " + r.getRecipe_id()+ " ORDER BY step_nr";
				
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(q2);
				while(rs2.next()) {
					Steps s = new Steps();
					s.setDescription(rs2.getString("step_description"));
					s.setNr(rs2.getInt("step_nr"));
					s.setRecipe_id(r.getRecipe_id());
					r.addSteps(s);
				}
				recipes.add(r);
			}	
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return recipes;
	}
	
	public ArrayList<Recipe> getChristmasRecipes() {
		ArrayList<Recipe> recipes = new ArrayList<>();
		
		String query = "SELECT * FROM recipes WHERE book_id = 5";
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			
			while(rs.next()) {
				Recipe r = new Recipe();
				r.setName(rs.getString("recipe_name"));
				r.setCalories_per_serving(rs.getDouble("calories_per_serving"));
				String diff[] = rs.getString("difficulty").split(" ", 2);
				r.setDifficulty(Double.parseDouble(diff[0]));
				r.setServing_size_in_grams(rs.getDouble("serving_size_in_grams"));
				r.setServings(rs.getInt("servings"));
				r.setRecipe_id(rs.getInt("recipe_id"));
				
				String q1 = "SELECT * FROM ingredients WHERE recipe_id = " + r.getRecipe_id();
				
				Statement stmt1 = conn.createStatement();
				ResultSet rs1 = stmt1.executeQuery(q1);
				while(rs1.next()) {
					Ingredients i = new Ingredients();
					i.setMeasuring_unit(rs1.getString("measuring_unit"));
					i.setName(rs1.getString("ingredient"));
					i.setQuantity(rs1.getDouble("quantity"));
					i.setRecipe_id(r.getRecipe_id());
					r.addIngredients(i);
				}
				
				String q2 = "SELECT * FROM steps WHERE recipe_id = " + r.getRecipe_id()+ " ORDER BY step_nr";
				
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(q2);
				while(rs2.next()) {
					Steps s = new Steps();
					s.setDescription(rs2.getString("step_description"));
					s.setNr(rs2.getInt("step_nr"));
					s.setRecipe_id(r.getRecipe_id());
					r.addSteps(s);
				}
				recipes.add(r);
			}	
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return recipes;
	}
	
	public ArrayList<Recipe> getCocktailRecipes() {
		ArrayList<Recipe> recipes = new ArrayList<>();
		
		String query = "SELECT * FROM recipes WHERE book_id = 6";
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			
			while(rs.next()) {
				Recipe r = new Recipe();
				r.setName(rs.getString("recipe_name"));
				r.setCalories_per_serving(rs.getDouble("calories_per_serving"));
				String diff[] = rs.getString("difficulty").split(" ", 2);
				r.setDifficulty(Double.parseDouble(diff[0]));
				r.setServing_size_in_grams(rs.getDouble("serving_size_in_grams"));
				r.setServings(rs.getInt("servings"));
				r.setRecipe_id(rs.getInt("recipe_id"));
				
				String q1 = "SELECT * FROM ingredients WHERE recipe_id = " + r.getRecipe_id();
				
				Statement stmt1 = conn.createStatement();
				ResultSet rs1 = stmt1.executeQuery(q1);
				while(rs1.next()) {
					Ingredients i = new Ingredients();
					i.setMeasuring_unit(rs1.getString("measuring_unit"));
					i.setName(rs1.getString("ingredient"));
					i.setQuantity(rs1.getDouble("quantity"));
					i.setRecipe_id(r.getRecipe_id());
					r.addIngredients(i);
				}
				
				String q2 = "SELECT * FROM steps WHERE recipe_id = " + r.getRecipe_id()+ " ORDER BY step_nr";
				
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(q2);
				while(rs2.next()) {
					Steps s = new Steps();
					s.setDescription(rs2.getString("step_description"));
					s.setNr(rs2.getInt("step_nr"));
					s.setRecipe_id(r.getRecipe_id());
					r.addSteps(s);
				}
				recipes.add(r);
			}	
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return recipes;
	}
	
	public ArrayList<Recipe> getDinnerRecipes() {
		ArrayList<Recipe> recipes = new ArrayList<>();
		
		String query = "SELECT * FROM recipes WHERE book_id = 3";
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			
			while(rs.next()) {
				Recipe r = new Recipe();
				r.setName(rs.getString("recipe_name"));
				r.setCalories_per_serving(rs.getDouble("calories_per_serving"));
				String diff[] = rs.getString("difficulty").split(" ", 2);
				r.setDifficulty(Double.parseDouble(diff[0]));
				r.setServing_size_in_grams(rs.getDouble("serving_size_in_grams"));
				r.setServings(rs.getInt("servings"));
				r.setRecipe_id(rs.getInt("recipe_id"));
				
				String q1 = "SELECT * FROM ingredients WHERE recipe_id = " + r.getRecipe_id();
				
				Statement stmt1 = conn.createStatement();
				ResultSet rs1 = stmt1.executeQuery(q1);
				while(rs1.next()) {
					Ingredients i = new Ingredients();
					i.setMeasuring_unit(rs1.getString("measuring_unit"));
					i.setName(rs1.getString("ingredient"));
					i.setQuantity(rs1.getDouble("quantity"));
					i.setRecipe_id(r.getRecipe_id());
					r.addIngredients(i);
				}
				
				String q2 = "SELECT * FROM steps WHERE recipe_id = " + r.getRecipe_id()+ " ORDER BY step_nr";
				
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(q2);
				while(rs2.next()) {
					Steps s = new Steps();
					s.setDescription(rs2.getString("step_description"));
					s.setNr(rs2.getInt("step_nr"));
					s.setRecipe_id(r.getRecipe_id());
					r.addSteps(s);
				}
				recipes.add(r);
			}	
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return recipes;
	}
	
	public ArrayList<Recipe> getSoupRecipes() {
		ArrayList<Recipe> recipes = new ArrayList<>();
		
		String query = "SELECT * FROM recipes WHERE book_id = 2";
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			
			while(rs.next()) {
				Recipe r = new Recipe();
				r.setName(rs.getString("recipe_name"));
				r.setCalories_per_serving(rs.getDouble("calories_per_serving"));
				String diff[] = rs.getString("difficulty").split(" ", 2);
				r.setDifficulty(Double.parseDouble(diff[0]));
				r.setServing_size_in_grams(rs.getDouble("serving_size_in_grams"));
				r.setServings(rs.getInt("servings"));
				r.setRecipe_id(rs.getInt("recipe_id"));
				
				String q1 = "SELECT * FROM ingredients WHERE recipe_id = " + r.getRecipe_id();
				
				Statement stmt1 = conn.createStatement();
				ResultSet rs1 = stmt1.executeQuery(q1);
				while(rs1.next()) {
					Ingredients i = new Ingredients();
					i.setMeasuring_unit(rs1.getString("measuring_unit"));
					i.setName(rs1.getString("ingredient"));
					i.setQuantity(rs1.getDouble("quantity"));
					i.setRecipe_id(r.getRecipe_id());
					r.addIngredients(i);
				}
				
				String q2 = "SELECT * FROM steps WHERE recipe_id = " + r.getRecipe_id()+ " ORDER BY step_nr";
				
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(q2);
				while(rs2.next()) {
					Steps s = new Steps();
					s.setDescription(rs2.getString("step_description"));
					s.setNr(rs2.getInt("step_nr"));
					s.setRecipe_id(r.getRecipe_id());
					r.addSteps(s);
				}
				recipes.add(r);
			}	
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return recipes;
	}
}
