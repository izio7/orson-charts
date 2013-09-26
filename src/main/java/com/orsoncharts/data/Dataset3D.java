/* ===========
 * OrsonCharts
 * ===========
 * 
 * (C)opyright 2013 by Object Refinery Limited.
 * 
 */

package com.orsoncharts.data;

/**
 * The base interface for datasets in OrsonCharts.  All datasets must support
 * the change event notification mechanism.  The idea is that when a dataset
 * is changed, an event is passed to the plot.  The plot can react to this
 * event, then pass on the change event to the chart.  The chart in turn will
 * pass on the event and this can result in the chart being repainted (for
 * example, if the chart is displayed in a {@link ChartPanel3D}.
 */
public interface Dataset3D {

    /**
     * Registers a change listener to receive notification of changes to the
     * dataset.
     * 
     * @param listener  the listener (<code>null</code> not permitted). 
     */
    void addChangeListener(Dataset3DChangeListener listener);  
  
    /**
     * De-registers a change listener so that it no longer receives notification
     * of changes to the dataset.
     * 
     * @param listener  the listener (<code>null</code> not permitted). 
     */
    void removeChangeListener(Dataset3DChangeListener listener);  

}
