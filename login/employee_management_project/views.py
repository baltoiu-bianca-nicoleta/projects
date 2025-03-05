import csv
from django.http import HttpResponse
from django.shortcuts import render, redirect
from .models import Problem, Employee
from django.utils import timezone

def add_problem(request):
    if request.method == 'POST':
        email = request.POST.get('email')
        text = request.POST.get('text')
        stable = request.POST.get('stable')
        employee, created = Employee.objects.get_or_create(name=email)
        
        Problem.objects.create(
            employee=employee,
            date=timezone.now(),
            stable=stable,  
            description=text
        )
        
        return redirect('add_problem')
    
    return render(request, 'employees/add_problem.html')


def generate_report(request):
   
    response = HttpResponse(content_type='text/csv')
    response['Content-Disposition'] = 'attachment; filename="report.csv"'
    
    
    writer = csv.writer(response)
    writer.writerow(['Employee', 'Date', 'Stable', 'Description'])  

    
    problems = Problem.objects.all()

    
    for problem in problems:
        writer.writerow([problem.employee.name, problem.date, problem.stable, problem.description])
    
    return response