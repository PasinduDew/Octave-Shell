# Octave-Shell
A Octave Shell Like Command Line Application

* Program Name: OctaveShell
* Description: This CLI(Command Line Interface) Application illustrates the Operational Behavior 
	       which is happening under the hood of the Octave Shell.  
* Programming Language: Java (java version "1.8.0_231")
* Author: L. A. P. J. Dewapriya - 17000262
* Date: 28 January 2020

* How to Run
	1. Compile the Source Code -> javac OctaveShell.java
	2. Run the Program         -> java OctaveShell

		            OR

	1. Simply Run the RunMe.cmd Command Line Script in Windows Operating System

-------------------------------------------------------------------------------------------------

Note: All the Values should be Integers

*Operations of the Shell

	1. Variable Declaration and Assignment 
		Syntax -> <variable_name> = <value>
		
		Scalar
		>>scalar_name = 25

		Matrix
		>> matrix_name = [ 1 2 3; 4 5 6]

		Vector
		>> vector_name = [1 2 3]
	        >> vector_name = [1; 2; 3]

	2. Scalar vs Scalar Operations

		>> 25 + 22
		>> 25 - 22
		>> 25 * 22
		>> 25 / 22

		>> x = 25
		>> y = 25
		>> x + y
		>> x - y
		>> x * y
		>> x / y

	3. Scalar vs Vector/Matrix Operations

		>> matA = [1 2 3; 4 5 6; 7 8 9]
		>> 2 + matA
		>> 2 - matA
		>> 2 * matA

		>> x = 25
		>> x + matA
		>> x - matA
		>> x * matA

	4. Vector/Matrix vs Vector/Matrix Operations

		>> matA = [1 1 1; 1 1 1; 1 1 1]
		>> matB = [2 2 2; 3 3 3; 8 8 8]

		>> matA + matB
		>> matA - matB
		>> matA * matB

	5. Vector/Matrix Element-Wise Multiplication

		>> matA .* matB

	6. To View a Variable Created

		>> variable_name

Note: The Vector/Matrix Operations only works on Vectors/Matrices have valid dimensions
		
************************************************** Thank You ************************************************
		
