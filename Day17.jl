#************************************************************************
# Stamp, "Mathematical Programming Modelling" (42112)
# Intro definitions

#************************************************************************
using JuMP
using GLPK
using Printf
#************************************************************************

#************************************************************************
# Data
xLower = 20
xUpper = 30
yLower = -10
yHigher = -5
M = 99999999
n = 10
#************************************************************************

#************************************************************************
# Model
model = Model(GLPK.Optimizer)
#set_optimizer_attributes(model, "OutputFlag" => 0)
println("Using solver: >",solver_name(model), "<")

@variable(model, x[1:n], Int)
@variable(model, isMax[1:n], Bin)
@variable(model, inTarget[1:n], Bin)
@variable(model, z[1:n], Int)
@variable(model, y[1:n], Int)
@variable(model, height[1:n], Int)
@variable(model, distance[1:n], Int)

@objective(model, Max, sum(z[i] for i=1:n))

@constraint(model, testX, x[1]==9)
@constraint(model, testY, y[1]== 4)


#@constraint(model, targetHigherY[i=1:n], height[i] <= yHigher + M*(1-inTarget[i]))
#@constraint(model, targetLowerY[i=1:n], height[i] + M*(1-inTarget[i]) >= yLower)
#@constraint(model, targetHigherX[i=1:1000], distance[i] <= xHigher + M*(1-inTarget[i]))
#@constraint(model, targetLowerX[i=1:1000], distance[i] + M*(1-inTarget[i]) >= xLower)

#@constraint(model, mustHitTarget, sum(inTarget[i] for i=1:n) >= 1)



#@constraint(model, zhz, z == 1000)
@constraint(model, xDrag[i=1:(n-1)], y[i+1] == y[i]-1 )
@constraint(model, ydrag[i=1:i], x[i] ==  )

@constraint(model, initHeight, height[1] == 0 )
@constraint(model, initDistance, distance[1] == 0 )

@constraint(model, calcHeight[i=1:(n-1)], height[i+1] == height[i] + y[i] )
@constraint(model, calcDistance[i=1:(n-1)], distance[i+1] == distance[i] + x[i] )

@constraint(model, isMaxCons[i=1:n, j=1:n], height[i] >= height[j] - M*isMax[i])
@constraint(model, onlyOneMax, sum(isMax[i] for i=1:n) == 1)
@constraint(model, maxHeight[i=1:n], z[i] <= height[i] )
#@constraint(model, maxHeightCons[i=1:n], z[i] <= M*isMax[i] )


#************************************************************************
# solve

#println("Using Gurobi optimizer version: ",Gurobi.version)
optimize!(model)
println("Termination status: $(termination_status(model))")
println("result: ", (termination_status(model) == MOI.OPTIMAL  ? "Optimal" : "NOT-Optimal") )
println("has values: ", (has_values(model) ? "Yes" : "No") )
println("Solve time: $(solve_time(model))")
#************************************************************************

#************************************************************************
# Report results
let
if termination_status(model) == MOI.OPTIMAL
    println("RESULTS:")
    println("model result: $(objective_value(model))")
    #println("x:", round.(Int,value.(x)))   # this round is short for Int(round(x))
    for i=1:n
        println("x: $(value(x[i]))")
        println("y: $(value(y[i]))")
        println("height: $(value(height[i]))")
        println("isMax: $(value(isMax[i]))")
        println("z: $(value(z[i]))")
        println("")
    end
else
  println("  No solution")
end
end
#************************************************************************


#************************************************************************
