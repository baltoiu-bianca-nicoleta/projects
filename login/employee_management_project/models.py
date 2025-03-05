from django.db import models

class Employee(models.Model):
    name = models.CharField(max_length=100)

    def __str__(self):
        return self.name

class Problem(models.Model):
    employee = models.ForeignKey(Employee, on_delete=models.CASCADE)
    date = models.DateField()
    stable = models.IntegerField(choices=[(i, f"Stable {i}") for i in range(1, 7)])
    description = models.TextField()

    def __str__(self):
        return f"{self.employee.name} -  Stable {self.stable} - {self.date}"

class CalfProblem(models.Model):
    employee = models.ForeignKey(Employee, on_delete=models.CASCADE)
    date = models.DateField()
    description = models.TextField()

class FNCProblem(models.Model):
    employee = models.ForeignKey(Employee, on_delete=models.CASCADE)
    date = models.DateField()
    description = models.TextField()

class WarehouseProblem(models.Model):
    employee = models.ForeignKey(Employee, on_delete=models.CASCADE)
    date = models.DateField()
    description = models.TextField()

class PremisesProblem(models.Model):
    employee = models.ForeignKey(Employee, on_delete=models.CASCADE)
    date = models.DateField()
    description = models.TextField()