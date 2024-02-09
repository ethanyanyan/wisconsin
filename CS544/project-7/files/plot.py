import pandas as pd
import matplotlib.pyplot as plt
import json
import os

def read_latest_month_data(partition_files, months):
    month_data = {}  # Dictionary to store month-year and average temperature
    for file in partition_files:
        if os.path.exists(file):
            with open(file, 'r') as f:
                data = json.load(f)
                for month in months:
                    if month in data:
                        years = data[month].keys()
                        latest_year = max(years, key=lambda y: (y, data[month][y]['end']))  # Find the latest year based on 'end' date
                        month_year_key = f"{month}-{latest_year}"  # Combine month and year
                        month_data[month_year_key] = data[month][latest_year]['avg']
    return month_data

def plot_monthly_averages(month_data):
    if not month_data:  # Check if month_data is empty
        print("No data available for plotting.")
        return

    month_series = pd.Series(month_data)
    fig, ax = plt.subplots()
    month_series.plot.bar(ax=ax)
    ax.set_ylabel('Avg. Max Temperature')
    ax.set_xlabel('Month-Year')
    plt.xticks(rotation=45)  # Rotate x-axis labels for better readability
    plt.tight_layout()
    plt.savefig("/files/month.svg")

if __name__ == "__main__":
    partition_files = [f'/files/partition-{i}.json' for i in range(4)]
    months = ['January', 'February', 'March']
    month_data = read_latest_month_data(partition_files, months)
    plot_monthly_averages(month_data)
