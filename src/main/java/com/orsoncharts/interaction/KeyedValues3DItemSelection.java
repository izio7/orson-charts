/* ===========================================================
 * Orson Charts : a 3D chart library for the Java(tm) platform
 * ===========================================================
 * 
 * (C)opyright 2013-2015, by Object Refinery Limited.  All rights reserved.
 * 
 * http://www.object-refinery.com/orsoncharts/index.html
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 * 
 * If you do not wish to be bound by the terms of the GPL, an alternative
 * commercial license can be purchased.  For details, please see visit the
 * Orson Charts home page:
 * 
 * http://www.object-refinery.com/orsoncharts/index.html
 * 
 */

package com.orsoncharts.interaction;

import com.orsoncharts.data.KeyedValues3D;
import com.orsoncharts.data.KeyedValues3DItemKey;

/**
 * An object representing a select of selected items for a {@link KeyedValues3D}
 * dataset.
 * 
 * @since 1.3
 */
public interface KeyedValues3DItemSelection {
    
    /**
     * Returns <code>true</code> if the specified key exists in the selection,
     * and <code>false</code> otherwise.
     * 
     * @param itemKey  the item key (<code>null</code> not permitted).
     * 
     * @return A boolean. 
     */
    boolean isSelected(KeyedValues3DItemKey itemKey);

}
