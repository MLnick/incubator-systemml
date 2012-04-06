package dml.runtime.instructions.CPInstructions;

import dml.runtime.controlprogram.ProgramBlock;
import dml.runtime.matrix.operators.Operator;
import dml.runtime.matrix.operators.UnaryOperator;
import dml.utils.DMLRuntimeException;
import dml.utils.DMLUnsupportedOperationException;

public class MatrixBuiltinCPInstruction extends BuiltinUnaryCPInstruction{
	public MatrixBuiltinCPInstruction(Operator op,
									  CPOperand in,
									  CPOperand out,
									  String instr){
		super(op, in, out, 1, instr);
	}

	@Override 
	public Data processInstruction(ProgramBlock pb) 
		throws DMLRuntimeException, DMLUnsupportedOperationException {
		
		MatrixObject in_mat = pb.getMatrixVariable(input1.get_name());
		
		UnaryOperator u_op = (UnaryOperator) optr;
			
		String output_name = output.get_name();
		
		MatrixObject sores = in_mat.unaryOperations(u_op, (MatrixObject)pb.getVariable(output_name));
				
		pb.setVariableAndWriteToHDFS(output_name, sores);
		return sores;
	}
}
