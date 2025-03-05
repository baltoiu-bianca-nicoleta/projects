from employees.models import Employee, Problem


emp = Employee(name="John Doe")
emp.save()


prob = Problem(employee=emp, date="2024-09-12", stable=3, description="Test problem")
prob.save()


employees = Employee.objects.all()
print(employees)


problems = Problem.objects.all()
print(problems)
